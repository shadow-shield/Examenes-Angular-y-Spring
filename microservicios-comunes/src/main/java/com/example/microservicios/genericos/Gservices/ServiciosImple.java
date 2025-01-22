package com.example.microservicios.genericos.Gservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class ServiciosImple<E,R extends JpaRepository<E,Long>> implements ServiceGenericos<E> {

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

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		
		return repositoryGe.findAll(pageable);
	}

	
}
