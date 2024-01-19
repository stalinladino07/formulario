package com.test.form.services;

import com.test.form.model.Users;
import com.test.form.model.Response;
import com.test.form.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users create (Users users) {
        return usersRepository.save(users);
    }

    public List<Users> getAllStores() {
        return usersRepository.findAll(Sort.by(Sort.Direction.ASC, "cedula"));
    }

    public void delete (Users users) {
         usersRepository.delete(users);
    }

    public ResponseEntity<Response> update (String cedula, Users users) {
        Response resp = new Response();
        if (usersRepository.existsById(Long.parseLong(cedula))) {
            users.setCedula(cedula);
            usersRepository.save(users);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Registro actualizado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró : " + cedula);
            return ResponseEntity.badRequest().body(resp);
        }
    }

    public Optional<Users> getById (String id) {
        return usersRepository.findById(Long.parseLong(id));
    }

    public Response login (Users users) {
        Response resp = new Response();
        try {
        Users user = usersRepository.getLogin(users.getEmail(),users.getPassword());
        if (user != null) {
            resp.setMessage("Sucessfull");
            resp.setData(user);
        } else {
            resp.setMessage("Invalid User");
            resp.setData(null);
        }
       return resp;
        } catch (Exception e) {
            resp.setMessage("Invalid User");
            return resp;
        }
    }


    public ResponseEntity<Response> deleteById (String idStore) {
        Response resp = new Response();
        if (usersRepository.existsById(Long.parseLong(idStore))) {
            usersRepository.deleteById(Long.parseLong(idStore));
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Storeo eliminado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró el storeo con ID: " + idStore);
            return ResponseEntity.badRequest().body(resp);
        }
    }

}
