package com.example.microservicios.genericos.Gservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class ServiciosImple<E,R extends CrudRepository<E,Long>> implements ServiceGenericos<E> {

    @Autowired
    protected R repositoryGe;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repositoryGe.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repositoryGe.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repositoryGe.save(entity);
    }


    @Override
    @Transactional
    public void delete(Long id) {
    	repositoryGe.deleteById(id);
    }

	
}
