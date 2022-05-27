package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
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
@RequestMapping ( value = "api/slides" )
@PermitAll
public class SlideApi {

	private final SlideService slideService;


	@Autowired
	public SlideApi( final SlideService slideService ) {

		this.slideService = slideService;
	}


	public Optional< Slide > get( UUID id ) {

		return slideService.get( id );
	}

	public Slide create( Slide entity ) {

		return slideService.create( entity );
	}

	public Slide update( Slide entity ) {

		return slideService.create( entity );
	}


	public void delete( UUID id ) {

		slideService.delete( id );
	}


	public Page< Slide > list( Pageable pageable ) {

		return slideService.list( pageable );
	}


	@GetMapping ( "all" )
	public List< Slide > list() {

		return slideService.list();
	}


	public List< Slide > list( final Sort sort ) {

		return slideService.list( sort );
	}


	public int count() {

		return ( int ) slideService.count();
	}

}
