package com.test.form.services;

import com.test.form.model.Product;
import com.test.form.model.Response;
import com.test.form.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create (Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "idProduct"));
    }

    public void delete (Product product) {
         productRepository.delete(product);
    }

    public ResponseEntity<Response> update (Long idProduct, Product product) {
        Response resp = new Response();
        if (productRepository.existsById(idProduct)) {
            product.setIdProduct(idProduct);
            productRepository.save(product);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Producto actualizado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró el producto con ID: " + idProduct);
            return ResponseEntity.badRequest().body(resp);
        }
    }

    public Optional<Product> getById (Long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<Response> deleteById (Long idProduct) {
        Response resp = new Response();
        if (productRepository.existsById(idProduct)) {
            productRepository.deleteById(idProduct);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Producto eliminado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró el producto con ID: " + idProduct);
            return ResponseEntity.badRequest().body(resp);
        }
    }

    public List<Product> getByIdStore(Long id) {
        return productRepository.getProductByStore(id);
    }

}
