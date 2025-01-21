package com.example.microservicios.usuarios.Controllers;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gcontroller.GController;
import com.example.microservicios.usuarios.services.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/alumno")
public class AlumnoController extends GController<Alumno, AlumnoService> {
  


   @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Alumno alumno) {
       Optional<Alumno> alumno1 = service.findById(id);

       if (alumno1.isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       Alumno alumDb = alumno1.get();
       alumDb.setNombre(alumno.getNombre());
       alumDb.setApellido(alumno.getApellido());
       alumDb.setEmail(alumno.getEmail());
       return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumDb));
   }

   

}
