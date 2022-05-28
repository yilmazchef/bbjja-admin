package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping ( value = EndPoints.PAGE_CLASS_LEVEL )
@PermitAll
public class PageApi {

	private final PageService pageService;


	@Autowired
	public PageApi( final PageService pageService ) {

		this.pageService = pageService;
	}


	@GetMapping ( EndPoints.PAGE_GET_BY_ID )
	public Optional< Page > get( @PathVariable @NotNull final UUID id ) {

		return pageService.get( id );
	}


	@PostMapping ( EndPoints.PAGE_CREATE )
	public Page create( @RequestBody @Valid final Page entity ) {

		return pageService.create( entity );
	}


	@PutMapping ( EndPoints.PAGE_UPDATE_BY_EXAMPLE )
	public Page update( @RequestBody @Valid final Page entity ) {

		return pageService.create( entity );
	}


	@DeleteMapping ( EndPoints.PAGE_DELETE_BY_ID )
	public void delete( @PathVariable @NotNull final UUID id ) {

		pageService.delete( id );
	}


	@GetMapping ( EndPoints.PAGE_LIST_IN_PAGES )
	public org.springframework.data.domain.Page< Page > list( @PathVariable @NotNull @PositiveOrZero final Integer page ) {

		return pageService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.PAGE_LIST_ALL )
	public List< Page > list() {

		return pageService.list();
	}


	@GetMapping ( EndPoints.PAGE_LIST_SORTED )
	public List< Page > list( @RequestBody @NotNull final Sort sort ) {

		return pageService.list( sort );
	}


	@GetMapping ( EndPoints.PAGES_COUNT )
	public int count() {

		return pageService.count();
	}

}
