package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping ( value = "api/pages" )
@PermitAll
public class PageApi {

	private final PageService pageService;


	@Autowired
	public PageApi( final PageService pageService ) {

		this.pageService = pageService;
	}


	public Optional< Page > get( UUID id ) {

		return pageService.get( id );
	}


	public Page create( Page entity ) {

		return pageService.create( entity );
	}


	public Page update( Page entity ) {

		return pageService.create( entity );
	}


	public void delete( UUID id ) {

		pageService.delete( id );
	}


	public org.springframework.data.domain.Page< Page > list( Pageable pageable ) {

		return pageService.list( pageable );
	}


	@GetMapping ( "all" )
	public List< Page > list() {

		return pageService.list();
	}


	public List< Page > list( final Sort sort ) {

		return pageService.list( sort );
	}


	public int count() {

		return ( int ) pageService.count();
	}

}
