package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = "api/subscribers" )
@PermitAll
public class SubscriberApi {

	private final SubscriberService repository;


	@Autowired
	public SubscriberApi( final SubscriberService repository ) {

		this.repository = repository;
	}


	public Optional< Subscriber > get( UUID id ) {

		return repository.get( id );
	}


	public Optional< Subscriber > get( final String email ) {

		return repository.get( email );
	}


	public Subscriber update( Subscriber entity ) {

		return repository.update( entity );
	}


	public void delete( UUID id ) {

		repository.delete( id );
	}


	public Page< Subscriber > list( Pageable pageable ) {

		return repository.list( pageable );
	}


	public List< Subscriber > list() {

		return repository.list();
	}


	public List< Subscriber > list( final Sort sort ) {

		return repository.list( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
