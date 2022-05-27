package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.service.OfferService;
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
@RequestMapping ( value = "api/offers" )
@PermitAll
public class OfferApi {

	private final OfferService repository;


	@Autowired
	public OfferApi( final OfferService repository ) {

		this.repository = repository;
	}


	public Optional< Offer > get( UUID id ) {

		return repository.get( id );
	}


	public Offer create( Offer entity ) {

		return repository.create( entity );
	}


	public Offer update( Offer entity ) {

		return repository.update( entity );
	}


	public void delete( UUID id ) {

		repository.delete( id );
	}


	public Page< Offer > list( Pageable pageable ) {

		return repository.list( pageable );
	}


	public List< Offer > list() {

		return repository.list();
	}


	public List< Offer > list( final Sort sort ) {

		return repository.list( sort );
	}


	public int count() {

		return repository.count();
	}

}
