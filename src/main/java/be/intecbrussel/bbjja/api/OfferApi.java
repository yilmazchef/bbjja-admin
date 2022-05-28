package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.OfferService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = EndPoints.OFFER_CLASS_LEVEL )
@PermitAll
public class OfferApi {

	private final OfferService offerService;
	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public OfferApi( final OfferService offerService, final AuthenticatedUser authenticatedUser ) {

		this.offerService = offerService;
		this.authenticatedUser = authenticatedUser;
	}


	@GetMapping ( EndPoints.OFFER_GET_BY_ID )
	public Optional< Offer > get( @PathVariable final UUID id ) {

		return offerService.get( id );
	}


	@PostMapping ( EndPoints.OFFER_CREATE )
	public Offer create( @RequestBody @Valid final Offer entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return offerService.create( entity );
	}


	@PutMapping ( EndPoints.OFFER_UPDATE_BY_EXAMPLE )
	public Offer update( @RequestBody @Valid final Offer entity ) {

		return offerService.update( entity );
	}


	@DeleteMapping ( EndPoints.OFFER_DELETE_BY_ID )
	public void delete( @PathVariable @NotNull final UUID id ) {

		offerService.delete( id );
	}


	@GetMapping ( EndPoints.OFFERS_LIST_IN_PAGES )
	public Page< Offer > list( @PathVariable @NotNull @PositiveOrZero final Integer page ) {

		return offerService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.OFFERS_LIST_ALL )
	public List< Offer > list() {

		return offerService.list();
	}


	@GetMapping ( EndPoints.OFFERS_LIST_SORTED )
	public List< Offer > list( @RequestBody @NotNull final Sort sort ) {

		return offerService.list( sort );
	}


	@GetMapping ( EndPoints.OFFERS_COUNT )
	public int count() {

		return offerService.count();
	}

}
