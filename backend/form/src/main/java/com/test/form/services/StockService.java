package com.test.form.services;

import com.test.form.model.*;
import com.test.form.repository.BillsProductRepository;
import com.test.form.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private BillsProductRepository billsProductRepository;

    public Bills create (StockStore stockStore) {
        Bills createBills = new Bills();
        createBills.setCedula(stockStore.getUser().getCedula());
        createBills.setIva(stockStore.getIva());
        createBills.setSubtotal(stockStore.getSubtotal());
        createBills.setTotal(stockStore.getTotal());
        Bills resp = stockRepository.save(createBills);

        for (Product product:stockStore.getProducts()) {
            BillsProducts billp = new BillsProducts();
            billp.setIdbills(resp.getIdbills());
            billp.setIdproduct(product.getIdProduct());
            billsProductRepository.save(billp);
        }

        return resp;
    }

    public List<Bills> getAllStock() {
        return stockRepository.findAll();
    }

    public void delete (Bills bills) {
        stockRepository.delete(bills);
    }

    public Optional<Bills> getById (Long id) {
        return stockRepository.findById(id);
    }

    public void deleteById (Long id) {
        stockRepository.deleteById(id);
    }

    public StockStore getBillProduct (Long id) {
        List<Tuple> tuples = stockRepository.getBillProduct(id);
        //StockStore resultados = new StockStore();
        StockStore stockStore = new StockStore();

        for (Tuple tuple : tuples) {

            BigDecimal ivaAsBigDecimal = tuple.get("iva", BigDecimal.class);
            Double ivaAsDouble = ivaAsBigDecimal != null ? ivaAsBigDecimal.doubleValue() : null;
            stockStore.setIva(ivaAsDouble);

            BigDecimal subtotalAsBigDecimal = tuple.get("subtotal", BigDecimal.class);
            Double subtotalAsDouble = subtotalAsBigDecimal != null ? subtotalAsBigDecimal.doubleValue() : null;
            stockStore.setSubtotal(subtotalAsDouble);

            BigDecimal totalAsBigDecimal = tuple.get("total", BigDecimal.class);
            Double totalAsDouble = totalAsBigDecimal != null ? totalAsBigDecimal.doubleValue() : null;
            stockStore.setTotal(totalAsDouble);

            stockStore.setUser(new Users());
            stockStore.getUser().setCedula(tuple.get("cedula", String.class));
            stockStore.getUser().setName(tuple.get("username", String.class));
            Product prd = new Product();

            prd.setIdProduct(Long.parseLong(tuple.get("idproduct", Integer.class).toString()));

            BigDecimal priceAsBigDecimal = tuple.get("price", BigDecimal.class);
            Double priceAsDouble = priceAsBigDecimal != null ? priceAsBigDecimal.doubleValue() : null;
            prd.setPrice(priceAsDouble);

            prd.setName(tuple.get("name", String.class));

            stockStore.setProducts(new ArrayList<Product>());
            stockStore.getProducts().add(prd);
        }
       // resultados = stockStore
        return stockStore;
    }

    public ResponseEntity<Response> update (Long idBills, StockStore stock) {
        Response resp = new Response();
        Bills updateBill = new Bills();
        if (stockRepository.existsById(idBills)) {

            updateBill.setTotal(stock.getTotal());
            updateBill.setSubtotal(stock.getSubtotal());
            updateBill.setIva(stock.getIva());
            updateBill.setIdbills(idBills);
            updateBill.setCedula(stock.getUser().getCedula());
            stockRepository.save(updateBill);

            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Usuario actualizado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró la factura con ID: " + idBills);
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
