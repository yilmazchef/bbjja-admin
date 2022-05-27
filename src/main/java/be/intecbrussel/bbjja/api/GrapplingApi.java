package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Grappling;
import be.intecbrussel.bbjja.data.service.GrapplingService;
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
@RequestMapping ( value = "api/grappling" )
@PermitAll
public class GrapplingApi {

	private final GrapplingService repository;


	@Autowired
	public GrapplingApi( final GrapplingService repository ) {

		this.repository = repository;
	}


	public Optional< Grappling > get( UUID id ) {

		return repository.get( id );
	}


	public Grappling create( Grappling entity ) {

		return repository.create( entity );
	}


	public Grappling update( Grappling entity ) {

		return repository.update( entity );
	}


	public void delete( UUID id ) {

		repository.delete( id );
	}


	public Page< Grappling > list( Pageable pageable ) {

		return repository.list( pageable );
	}


	public List< Grappling > list() {

		return repository.list();
	}


	public List< Grappling > school() {

		return repository.school();
	}


	public List< Grappling > street() {

		return repository.street();
	}


	public List< Grappling > list( final Sort sort ) {

		return repository.list( sort );
	}


	public int count() {

		return repository.count();
	}

}
