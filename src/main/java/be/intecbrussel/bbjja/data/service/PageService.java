package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PageService {

	private final PageRepository repository;


	@Autowired
	public PageService( final PageRepository repository ) {

		this.repository = repository;
	}


	public Optional< Page > get( UUID id ) {

		return repository.findById( id );
	}


	public Page create( Page entity ) {

		return repository.save( entity );
	}


	public Page update( Page entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public org.springframework.data.domain.Page< Page > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Page > list() {

		return repository.findAll();
	}


	public List< Page > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
