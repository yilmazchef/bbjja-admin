package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Grappling;
import be.intecbrussel.bbjja.data.service.GrapplingService;
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
@RequestMapping ( value = "api/grappling" )
@PermitAll
public class GrapplingApi {

	private final GrapplingService grapplingService;


	@Autowired
	public GrapplingApi( final GrapplingService grapplingService ) {

		this.grapplingService = grapplingService;
	}


	public Optional< Grappling > get( UUID id ) {

		return grapplingService.get( id );
	}


	public Grappling create( Grappling entity ) {

		return grapplingService.create( entity );
	}


	public Grappling update( Grappling entity ) {

		return grapplingService.update( entity );
	}


	public void delete( UUID id ) {

		grapplingService.delete( id );
	}


	public Page< Grappling > list( Pageable pageable ) {

		return grapplingService.list( pageable );
	}


	@GetMapping ( "all" )
	public List< Grappling > list() {

		return grapplingService.list();
	}


	public List< Grappling > school() {

		return grapplingService.school();
	}


	public List< Grappling > street() {

		return grapplingService.street();
	}


	public List< Grappling > list( final Sort sort ) {

		return grapplingService.list( sort );
	}


	public int count() {

		return grapplingService.count();
	}

}
