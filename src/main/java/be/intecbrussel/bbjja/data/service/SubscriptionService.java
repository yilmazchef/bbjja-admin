package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {

	private final SubscriberRepository repository;


	@Autowired
	public SubscriptionService( final SubscriberRepository repository ) {

		this.repository = repository;
	}


	public Optional< Subscriber > get( UUID id ) {

		return repository.findById( id );
	}


	public Optional< Subscriber > get( final String email ) {

		return repository.findByEmailIgnoreCase( email );
	}


	public Subscriber update( Subscriber entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< Subscriber > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Subscriber > list() {

		return repository.findAll();
	}


	public List< Subscriber > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
