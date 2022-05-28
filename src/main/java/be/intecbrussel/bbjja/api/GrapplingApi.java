package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Grappling;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.GrapplingService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = EndPoints.GRAPPLING_CLASS_LEVEL )
@PermitAll
public class GrapplingApi {

	private final GrapplingService grapplingService;
	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public GrapplingApi( final GrapplingService grapplingService, final AuthenticatedUser authenticatedUser ) {

		this.grapplingService = grapplingService;
		this.authenticatedUser = authenticatedUser;
	}


	@GetMapping ( EndPoints.GRAPPLING_GET_BY_ID )
	public Optional< Grappling > get( @PathVariable final UUID id ) {

		return grapplingService.get( id );
	}


	@PostMapping ( EndPoints.GRAPPLING_CREATE )
	public Grappling create( @RequestBody final Grappling entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return grapplingService.create( entity );
	}


	@PutMapping ( EndPoints.GRAPPLING_UPDATE_BY_EXAMPLE )
	public Grappling update( @RequestBody final Grappling entity ) {

		return grapplingService.update( entity );
	}


	@DeleteMapping ( EndPoints.GRAPPLING_DELETE_BY_ID )
	public void delete( @PathVariable final UUID id ) {

		grapplingService.delete( id );
	}


	@GetMapping ( EndPoints.GRAPPLING_LIST_IN_PAGES )
	public Page< Grappling > list( @PathVariable final Integer page ) {

		return grapplingService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.GRAPPLING_LIST_ALL )
	public List< Grappling > list() {

		return grapplingService.list();
	}


	@GetMapping ( EndPoints.GRAPPLING_LIST_SCHOOL )
	public List< Grappling > school() {

		return grapplingService.school();
	}


	@GetMapping ( EndPoints.GRAPPLING_LIST_STREET )
	public List< Grappling > street() {

		return grapplingService.street();
	}


	@GetMapping ( EndPoints.GRAPPLING_LIST_SORTED )
	public List< Grappling > list( @RequestBody final Sort sort ) {

		return grapplingService.list( sort );
	}


	@GetMapping ( EndPoints.GRAPPLING_COUNT )
	public int count() {

		return grapplingService.count();
	}

}
