package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PageApi {

	private final PageService repository;


	@Autowired
	public PageApi( final PageService repository ) {

		this.repository = repository;
	}


	public Optional< Page > get( UUID id ) {

		return repository.get( id );
	}


	public Page create( Page entity ) {

		return repository.create( entity );
	}


	public Page update( Page entity ) {

		return repository.create( entity );
	}


	public void delete( UUID id ) {

		repository.delete( id );
	}


	public org.springframework.data.domain.Page< Page > list( Pageable pageable ) {

		return repository.list( pageable );
	}


	public List< Page > list() {

		return repository.list();
	}


	public List< Page > list( final Sort sort ) {

		return repository.list( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
