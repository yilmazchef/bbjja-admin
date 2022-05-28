package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = "api/subscribers" )
@PermitAll
public class SubscriberApi {

	private final SubscriberService subscriberService;
	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public SubscriberApi( final SubscriberService subscriberService, final AuthenticatedUser authenticatedUser ) {

		this.subscriberService = subscriberService;
		this.authenticatedUser = authenticatedUser;
	}


	@GetMapping ( EndPoints.SUBSCRIBER_GET_BY_ID )
	public Optional< Subscriber > get( @PathVariable @NotNull final UUID id ) {

		return subscriberService.get( id );
	}


	@GetMapping ( EndPoints.SUBSCRIBER_GET_BY_EMAIL )
	public Optional< Subscriber > get( @PathVariable @NotNull final String email ) {

		return subscriberService.get( email );
	}


	@PostMapping ( EndPoints.USERS_CREATE )
	public Subscriber create( @RequestBody @Valid final Subscriber entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return subscriberService.create( entity );
	}


	@PutMapping ( EndPoints.SUBSCRIBER_UPDATE_BY_EXAMPLE )
	public Subscriber update( @RequestBody @Valid final Subscriber entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setDateModified( LocalDateTime.now() );
			entity.setModifiedBy( u.getUsername() );
		} );

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
