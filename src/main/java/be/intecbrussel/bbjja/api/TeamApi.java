package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Team;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.TeamService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = EndPoints.EMPLOYEE_CLASS_LEVEL )
@PermitAll
public class TeamApi {

	private final TeamService teamService;
	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public TeamApi( final TeamService teamService, final AuthenticatedUser authenticatedUser ) {

		this.teamService = teamService;
		this.authenticatedUser = authenticatedUser;
	}


	@GetMapping ( EndPoints.EMPLOYEE_GET_BY_ID )
	public Optional< Team > get( @PathVariable @NotNull final UUID id ) {

		return teamService.get( id );
	}


	@GetMapping ( EndPoints.EMPLOYEE_GET_BY_EMAIL )
	public Optional< Team > get( @PathVariable @Valid final String email ) {

		return teamService.get( email );
	}


	@PostMapping ( EndPoints.EMPLOYEE_CREATE )
	public Team create( @RequestBody @Valid final Team entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return teamService.create( entity );
	}


	@PutMapping ( EndPoints.EMPLOYEE_UPDATE_BY_EXAMPLE )
	public Team update( final Team entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setDateModified( LocalDateTime.now() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return teamService.update( entity );
	}


	@DeleteMapping ( EndPoints.EMPLOYEE_DELETE_BY_ID )
	public void delete( @PathVariable final UUID id ) {

		teamService.delete( id );
	}


	@GetMapping ( EndPoints.EMPLOYEE_LIST_IN_PAGES )
	public Page< Team > list( @PathVariable final Integer page ) {

		return teamService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.EMPLOYEE_LIST_ALL )
	public List< Team > list() {

		return teamService.list();
	}


	@GetMapping ( EndPoints.EMPLOYEE_LIST_SORTED )
	public List< Team > list( final Sort sort ) {

		return teamService.list( sort );
	}


	@GetMapping ( EndPoints.EMPLOYEES_COUNT )
	public int count() {

		return teamService.count();
	}

}
