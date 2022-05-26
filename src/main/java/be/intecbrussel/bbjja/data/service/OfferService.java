package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

	private final OfferRepository repository;


	@Autowired
	public OfferService( final OfferRepository repository ) {

		this.repository = repository;
	}


	public Optional< Offer > get( UUID id ) {

		return repository.findById( id );
	}

	public Offer create( Offer entity ) {

		return repository.save( entity );
	}

	public Offer update( Offer entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< Offer > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Offer > list() {

		return repository.findAll();
	}


	public List< Offer > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
