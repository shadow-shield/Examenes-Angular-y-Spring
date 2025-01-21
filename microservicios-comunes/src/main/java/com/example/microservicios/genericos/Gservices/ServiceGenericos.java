package com.example.microservicios.genericos.Gservices;



import java.util.Optional;

public interface ServiceGenericos<E>{
    public Iterable <E> findAll();

    public Optional<E> findById(Long id);

    public E save(E entity);
    
    public void delete(Long id);
}
