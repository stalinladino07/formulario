package com.test.form.controller;

import com.test.form.model.Bills;
import com.test.form.services.BillsService;
import com.test.form.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping ("/api/order")

public class BillsController {

    @Autowired
    private BillsService billsService;


    @PostMapping("/create")
    @ResponseBody
    private ResponseEntity<Bills> createOrders (@RequestBody Bills order){
        Bills ordersCreate = billsService.createOrder(order);
        try {
            return ResponseEntity.created(new URI("/api/hulkstore/save"+ordersCreate.getIdbills())).body(ordersCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/getbystore/{id}")
    private ResponseEntity<List<Bills>> getStockByStore (@PathVariable Long id,
                                                               @RequestParam(name = "dateInit")
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateInit
            , @RequestParam(name = "dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd){
        List<Bills> stockResponse = billsService.getByOrder(id, dateInit, dateEnd);
        try {
            return ResponseEntity.ok(stockResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> deleteOrder (@PathVariable Long id){

        try {
            return billsService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };
}
