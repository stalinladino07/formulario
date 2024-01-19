package com.test.form.services;

import com.test.form.model.Response;
import com.test.form.model.Provider;
import com.test.form.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public Provider create (Provider users) {
        return providerRepository.save(users);
    }

    public List<Provider> getAllStores() {
        return providerRepository.findAll(Sort.by(Sort.Direction.ASC, "idprovider"));
    }

    public void delete (Provider users) {
         providerRepository.delete(users);
    }

    public ResponseEntity<Response> update (Long id, Provider provider) {
        Response resp = new Response();
        if (providerRepository.existsById(id)) {
            provider.setIdprovider(id);
            providerRepository.save(provider);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Registro actualizado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró : " + id);
            return ResponseEntity.badRequest().body(resp);
        }
    }

    public Optional<Provider> getById (Long id) {
        return providerRepository.findById(id);
    }


    public ResponseEntity<Response> deleteById (Long id) {
        Response resp = new Response();
        if (providerRepository.existsById(id)) {
            providerRepository.deleteById(id);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Storeo eliminado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró el storeo con ID: " + id);
            return ResponseEntity.badRequest().body(resp);
        }
    }

}
