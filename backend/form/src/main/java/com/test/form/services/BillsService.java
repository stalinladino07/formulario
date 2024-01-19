package com.test.form.services;

import com.test.form.repository.BillsRepository;
import com.test.form.repository.BillsProductRepository;
import com.test.form.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillsService {

    @Autowired
    private BillsRepository billsRepository;
    @Autowired
    private BillsProductRepository billsProductRepository;

    public Bills createOrder (Bills order) {
       /* try {

        Bills bills = new Bills();
        bills.setDateOrder(order.getDateOrder());
        Bills newOrder = billsRepository.save(bills);

        for (ProviderProduct product : order.getProducts()) {
            BillsProducts createProd = new BillsProducts();
            createProd.setIdOrder(newOrder.getIdOrder());
            createProd.setIdProduct(product.getIdProduct());
            productOrderRepository.save(createProd);
        }
            return newOrder;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }*/
        return null;

    }

    public List<Bills> getByOrder(Long id, LocalDate dateInit, LocalDate dateEnd) {

        List<Tuple> tuples = billsProductRepository.getOrderByClient(id, dateInit, dateEnd);
        List<Bills> resultados = new ArrayList<>();
      /*  for (Tuple tuple : tuples) {
            Bills stockStore = new Bills();
            stockStore.setDateOrder(tuple.get("dateorder", Date.class));
            stockStore.setStoreName(tuple.get("storeName", String.class));
            stockStore.setProductName(tuple.get("productName", String.class));

            BigDecimal availableAsBigDecimal = tuple.get("price", BigDecimal.class);
            Double availableAsDouble = availableAsBigDecimal != null ? availableAsBigDecimal.doubleValue() : null;
            stockStore.setPrice(availableAsDouble);

            resultados.add(stockStore);
        }*/
        return resultados;
    }

    public ResponseEntity<Response> deleteById (Long idOrder) {
        Response resp = new Response();
        if (billsRepository.existsById(idOrder)) {

            billsProductRepository.deleteProductOrder(idOrder);
            billsRepository.deleteById(idOrder);

            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Orden eliminado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró la Order con ID: " + idOrder);
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
