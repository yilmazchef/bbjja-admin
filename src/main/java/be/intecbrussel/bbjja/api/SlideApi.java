package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
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
@RequestMapping ( value = "api/slides" )
@PermitAll
public class SlideApi {

	private final SlideService repository;


	@Autowired
	public SlideApi( final SlideService repository ) {

		this.repository = repository;
	}


	public Optional< Slide > get( UUID id ) {

		return repository.get( id );
	}

	public Slide create( Slide entity ) {

		return repository.create( entity );
	}

	public Slide update( Slide entity ) {

		return repository.create( entity );
	}


	public void delete( UUID id ) {

		repository.delete( id );
	}


	public Page< Slide > list( Pageable pageable ) {

		return repository.list( pageable );
	}


	public List< Slide > list() {

		return repository.list();
	}


	public List< Slide > list( final Sort sort ) {

		return repository.list( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
