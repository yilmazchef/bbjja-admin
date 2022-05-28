package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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


	@GetMapping ( EndPoints.SUBSCRIBER_GET_BY_ID )
	public Optional< Subscriber > get( @PathVariable @NotNull final UUID id ) {

		return subscriberService.get( id );
	}


	@GetMapping ( EndPoints.SUBSCRIBER_GET_BY_EMAIL )
	public Optional< Subscriber > get( @PathVariable @NotNull final String email ) {

		return subscriberService.get( email );
	}


	@PutMapping ( EndPoints.SUBSCRIBER_UPDATE_BY_EXAMPLE )
	public Subscriber update( @RequestBody @Valid final Subscriber entity ) {

		return subscriberService.update( entity );
	}


	@DeleteMapping ( EndPoints.SUBSCRIBER_DELETE_BY_ID )
	public void delete( @PathVariable @NotNull final UUID id ) {

		subscriberService.delete( id );
	}


	@GetMapping ( EndPoints.SUBSCRIBERS_LIST_IN_PAGES )
	public Page< Subscriber > list( @PathVariable @NotNull @PositiveOrZero final Integer page ) {

		return subscriberService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.SUBSCRIBERS_LIST_ALL )
	public List< Subscriber > list() {

		return subscriberService.list();
	}


	@GetMapping ( EndPoints.SUBSCRIBERS_LIST_SORTED )
	public List< Subscriber > list( @RequestBody @Valid final Sort sort ) {

		return subscriberService.list( sort );
	}


	@GetMapping ( EndPoints.SUBSCRIBERS_COUNT )
	public int count() {

		return subscriberService.count();
	}

}
