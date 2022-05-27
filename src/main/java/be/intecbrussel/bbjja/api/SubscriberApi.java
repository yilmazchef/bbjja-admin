package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
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

	private final SubscriberService subscriberService;


	@Autowired
	public SubscriberApi( final SubscriberService subscriberService ) {

		this.subscriberService = subscriberService;
	}


	public Optional< Subscriber > get( UUID id ) {

		return subscriberService.get( id );
	}


	public Optional< Subscriber > get( final String email ) {

		return subscriberService.get( email );
	}


	public Subscriber update( Subscriber entity ) {

		return subscriberService.update( entity );
	}


	public void delete( UUID id ) {

		subscriberService.delete( id );
	}


	public Page< Subscriber > list( Pageable pageable ) {

		return subscriberService.list( pageable );
	}


	@GetMapping ( "all" )
	public List< Subscriber > list() {

		return subscriberService.list();
	}


	public List< Subscriber > list( final Sort sort ) {

		return subscriberService.list( sort );
	}


	public int count() {

		return ( int ) subscriberService.count();
	}

}
