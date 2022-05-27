package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.service.OfferService;
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
@RequestMapping ( value = "api/offers" )
@PermitAll
public class OfferApi {

	private final OfferService offerService;


	@Autowired
	public OfferApi( final OfferService offerService ) {

		this.offerService = offerService;
	}


	public Optional< Offer > get( UUID id ) {

		return offerService.get( id );
	}


	public Offer create( Offer entity ) {

		return offerService.create( entity );
	}


	public Offer update( Offer entity ) {

		return offerService.update( entity );
	}


	public void delete( UUID id ) {

		offerService.delete( id );
	}


	public Page< Offer > list( Pageable pageable ) {

		return offerService.list( pageable );
	}


	@GetMapping ( "all" )
	public List< Offer > list() {

		return offerService.list();
	}


	public List< Offer > list( final Sort sort ) {

		return offerService.list( sort );
	}


	public int count() {

		return offerService.count();
	}

}
