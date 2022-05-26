package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.dto.NewOfferRequest;
import be.intecbrussel.bbjja.data.dto.OfferResponse;
import be.intecbrussel.bbjja.data.dto.UpdateOfferRequest;
import be.intecbrussel.bbjja.data.mappers.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferService {

	private final OfferRepository repository;
	private final OfferMapper mapper;


	@Autowired
	public OfferService( final OfferRepository repository, final OfferMapper mapper ) {

		this.repository = repository;
		this.mapper = mapper;
	}


	public Optional< OfferResponse > get( UUID id ) {

		return repository
				.findById( id )
				.map( mapper :: offerToOfferResponse );
	}


	public OfferResponse create( NewOfferRequest newOfferRequest ) {

		return mapper
				.offerToOfferResponse(
						repository.save(
								mapper.newOfferRequestToOffer( newOfferRequest )
						)
				);
	}


	public OfferResponse update( UpdateOfferRequest updateOfferRequest ) {

		return mapper.offerToOfferResponse( repository.save(
				mapper.updateOfferRequestToOffer( updateOfferRequest )
		) );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public List< OfferResponse > list( Pageable pageable ) {

		return repository
				.findAll( pageable )
				.stream()
				.map( mapper :: offerToOfferResponse )
				.collect( Collectors.toUnmodifiableList() );
	}


	public List< OfferResponse > list() {

		return repository
				.findAll()
				.stream()
				.map( mapper :: offerToOfferResponse )
				.collect( Collectors.toUnmodifiableList() );
	}


	public List< OfferResponse > list( final Sort sort ) {

		return repository
				.findAll( sort )
				.stream()
				.map( mapper :: offerToOfferResponse )
				.collect( Collectors.toUnmodifiableList() );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
