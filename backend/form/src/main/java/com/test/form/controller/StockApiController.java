package com.test.form.controller;

import com.test.form.model.*;
import com.test.form.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping ("/api/stock")
public class StockApiController {

    @Autowired
    private StockService stockService;

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<Bills> saveStock (@RequestBody StockStore bills){
        Bills billsCreate = stockService.create(bills);

        try {
            return ResponseEntity.created(new URI("/api/stock/save"+ billsCreate.getIdbills())).body(billsCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/listall")
    private ResponseEntity<List<Bills>> listAllStock (){
        List<Bills> billsListAll = stockService.getAllStock();
        try {
            return ResponseEntity.ok(billsListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteStock (@PathVariable Long id){
        stockService.deleteById(id);
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };


    @GetMapping("/getbystore/{id}")
    private ResponseEntity<StockStore> getStockByStore (@PathVariable Long id){
       StockStore stockResponse = stockService.getBillProduct(id);
        try {
            return ResponseEntity.ok(stockResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @PutMapping("/{id}")
    @ResponseBody
    private ResponseEntity<Response> updateStock (@PathVariable Long id, @RequestBody StockStore stockStore){
        try {
            return stockService.update(id,stockStore);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
