package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.SlideService;
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
@RequestMapping ( value = EndPoints.SLIDE_CLASS_LEVEL )
@PermitAll
public class SlideApi {

	private final SlideService slideService;
	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public SlideApi( final SlideService slideService, final AuthenticatedUser authenticatedUser ) {

		this.slideService = slideService;
		this.authenticatedUser = authenticatedUser;
	}


	@GetMapping ( EndPoints.SLIDE_GET_BY_ID )
	public Optional< Slide > get( @PathVariable @NotNull final UUID id ) {

		return slideService.get( id );
	}


	@PostMapping ( EndPoints.SLIDE_CREATE )
	public Slide create( @RequestBody @Valid final Slide entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return slideService.create( entity );
	}


	@PutMapping ( EndPoints.SLIDE_UPDATE_BY_EXAMPLE )
	public Slide update( @RequestBody @Valid final Slide entity ) {

		return slideService.create( entity );
	}


	@DeleteMapping ( EndPoints.SLIDE_DELETE_BY_ID )
	public void delete( @PathVariable @NotNull final UUID id ) {

		slideService.delete( id );
	}


	@GetMapping ( EndPoints.SCHOOLS_LIST_IN_PAGES )
	public Page< Slide > list( @PathVariable @NotNull @PositiveOrZero final Integer page ) {

		return slideService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.SCHOOLS_LIST_ALL )
	public List< Slide > list() {

		return slideService.list();
	}


	@GetMapping ( EndPoints.SCHOOLS_LIST_SORTED )
	public List< Slide > list( @RequestBody @Valid final Sort sort ) {

		return slideService.list( sort );
	}


	@GetMapping ( EndPoints.SLIDES_COUNT )
	public int count() {

		return slideService.count();
	}

}
