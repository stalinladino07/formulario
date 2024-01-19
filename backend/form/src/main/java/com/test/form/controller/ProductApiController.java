package com.test.form.controller;

import com.test.form.model.Product;
import com.test.form.model.Response;
import com.test.form.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping ("/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<Product> saveProduct (@RequestBody Product product){
        Product productCreate = productService.create(product);

        try {
            return ResponseEntity.created(new URI("/api/hulkstore/save"+productCreate.getIdProduct())).body(productCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/listall")
    private ResponseEntity<List<Product>> getAllProducts (){
        List<Product> productListAll = productService.getAllProducts();
        try {
            return ResponseEntity.ok(productListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> deleteProduct (@PathVariable Long id){

        try {
            return productService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping(name="/getbyid/{id}", value = "{id}")
    private ResponseEntity<Optional<Product>> findById (@PathVariable Long id){
        Optional<Product> productResponse = productService.getById(id);
        try {
            return ResponseEntity.ok(productResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @PutMapping("/{id}")
    @ResponseBody
    private ResponseEntity<Response> updateProduct (@PathVariable Long id, @RequestBody Product product){
        try {
            return productService.update(id,product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/store/{id}")
    private ResponseEntity<List<Product>> getProductByStore (@PathVariable Long id){
        List<Product> listProductStore = productService.getByIdStore(id);
        try {
            return ResponseEntity.ok(listProductStore);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };
}
