package com.test.form.controller;

import com.test.form.model.Users;
import com.test.form.model.Response;
import com.test.form.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping ("/api/users")
public class UsersApiController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<Users> saveStore (@RequestBody Users users){
        Users usersCreate = usersService.create(users);

        try {
            return ResponseEntity.created(new URI("/api/hulkstore/save"+ usersCreate.getCedula())).body(usersCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/listall")
    private ResponseEntity<List<Users>> getAllStores (){
        List<Users> usersListAll = usersService.getAllStores();
        try {
            return ResponseEntity.ok(usersListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> deleteStore (@PathVariable String id){

        try {
            return usersService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping(name="/getbyid/{id}", value = "{id}")
    private ResponseEntity<Optional<Users>> findById (@PathVariable String id){
        Optional<Users> usersResponse = usersService.getById(id);
        try {
            return ResponseEntity.ok(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @PostMapping("/getLogin")
    @ResponseBody
    private ResponseEntity<Response> getLogin (@RequestBody Users users){
        Response usersResponse = usersService.login(users);
        try {
            return ResponseEntity.ok(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };


}
