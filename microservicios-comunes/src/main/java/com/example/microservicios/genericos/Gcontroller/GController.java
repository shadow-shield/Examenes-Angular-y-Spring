package com.example.microservicios.genericos.Gcontroller;


import com.example.microservicios.genericos.Gservices.ServiceGenericos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


public class GController <E, S extends ServiceGenericos<E>>{

    @Autowired
    protected S service;
  
   @GetMapping("/generic")
    public ResponseEntity<Iterable<E>> getUsers() {
       return ResponseEntity.ok().body(service.findAll());
   }

   @GetMapping("/genericget/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
       Optional<E> a = service.findById(id);
       if (a.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(a.get());
   }
   
   
   @PostMapping("/genericadd")
    public ResponseEntity<?> createUser(@RequestBody E entity) {
       E a = service.save(entity);
       return ResponseEntity.status(HttpStatus.CREATED).body(a);

   }

   @DeleteMapping("/genericdele/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
       Optional<E> entityDB = service.findById(id);
       if (entityDB.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       service.delete(id);
       return ResponseEntity.noContent().build();

   }

}
