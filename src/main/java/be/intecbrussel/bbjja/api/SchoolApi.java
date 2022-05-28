package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.School;
import be.intecbrussel.bbjja.data.service.SchoolService;
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
@RequestMapping ( value = EndPoints.SCHOOL_CLASS_LEVEL )
@PermitAll
public class SchoolApi {

	private final SchoolService schoolService;


	@Autowired
	public SchoolApi( final SchoolService schoolService ) {

		this.schoolService = schoolService;
	}


	@GetMapping ( EndPoints.SCHOOL_GET_BY_ID )
	public Optional< School > get( @PathVariable @NotNull final UUID id ) {

		return schoolService.get( id );
	}


	@PostMapping ( EndPoints.SCHOOL_CREATE )
	public School create( @RequestBody @Valid final School entity ) {

		return schoolService.create( entity );
	}


	@PutMapping ( EndPoints.SCHOOL_UPDATE_BY_EXAMPLE )
	public School update( @RequestBody @Valid final School entity ) {

		return schoolService.update( entity );
	}


	@DeleteMapping ( EndPoints.SCHOOL_DELETE_BY_ID )
	public void delete( @PathVariable @NotNull final UUID id ) {

		schoolService.delete( id );
	}


	@GetMapping ( EndPoints.SCHOOLS_LIST_IN_PAGES )
	public Page< School > list( @PathVariable @NotNull @PositiveOrZero final Integer page ) {

		return schoolService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.SCHOOLS_LIST_ALL )
	public List< School > list() {

		return schoolService.list();
	}


	@GetMapping ( EndPoints.SCHOOLS_LIST_SORTED )
	public List< School > list( @RequestBody @NotNull final Sort sort ) {

		return schoolService.list( sort );
	}


	@GetMapping ( EndPoints.SCHOOLS_COUNT )
	public int count() {

		return schoolService.count();
	}

}
