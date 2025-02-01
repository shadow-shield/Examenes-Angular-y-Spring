package com.example.microservicios.genericos.Gcontroller;


import com.example.microservicios.genericos.Gservices.ServiceGenericos;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GController <E, S extends ServiceGenericos<E>>{

    @Autowired
    protected S service;
  
   @GetMapping()
    public ResponseEntity<?> getUsers() {
       return ResponseEntity.ok().body(service.findAll());
   }
   
   @GetMapping("/pagina")
   public ResponseEntity<?> getUsersPorPaginas(Pageable pageable) {
      return ResponseEntity.ok().body(service.findAll(pageable));
  }

   @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
       Optional<E> a = service.findById(id);
       if (a.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(a.get());
   }
   
   
   @PostMapping()
	public ResponseEntity<?> createUser(@Valid @RequestBody E entity,BindingResult result) {
	   if(result.hasErrors()) {
		   return this.validar(result);
		   
	   }
	   
       E a = service.save(entity);
       return ResponseEntity.status(HttpStatus.CREATED).body(a);

   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
       Optional<E> entityDB = service.findById(id);
       if (entityDB.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       service.delete(id);
       return ResponseEntity.noContent().build();

   }
   
   protected ResponseEntity<?>validar(BindingResult result){
	   Map<String, Object>errores= new HashMap<>();
	   result.getFieldErrors().forEach(err->{
		   errores.put(err.getField(), "El campo "+err.getField()+ "  "+err.getDefaultMessage());
	   });
	   
	   return ResponseEntity.badRequest().body(errores);
   }



}
