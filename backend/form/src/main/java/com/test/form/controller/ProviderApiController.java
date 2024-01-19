package com.test.form.controller;

import com.test.form.model.Response;
import com.test.form.model.Provider;
import com.test.form.services.ProviderService;
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
@RequestMapping ("/api/provider")
public class ProviderApiController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<Provider> saveStore (@RequestBody Provider provider){
        Provider usersCreate = providerService.create(provider);

        try {
            return ResponseEntity.created(new URI("/api/hulkstore/save"+ usersCreate.getIdprovider())).body(usersCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/listall")
    private ResponseEntity<List<Provider>> getAllStores (){
        List<Provider> usersListAll = providerService.getAllStores();
        try {
            return ResponseEntity.ok(usersListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> deleteStore (@PathVariable Long id){

        try {
            return providerService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping(name="/getbyid/{id}", value = "{id}")
    private ResponseEntity<Optional<Provider>> findById (@PathVariable Long id){
        Optional<Provider> usersResponse = providerService.getById(id);
        try {
            return ResponseEntity.ok(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @PutMapping("/{id}")
    @ResponseBody
    private ResponseEntity<Response> updateStore (@PathVariable Long id, @RequestBody Provider provider){
        try {
            return providerService.update(id, provider);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

}
