package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.School;
import be.intecbrussel.bbjja.data.service.SchoolService;
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
@RequestMapping ( value = "api/schools_offered" )
@PermitAll
public class SchoolApi {

	private final SchoolService schoolService;


	@Autowired
	public SchoolApi( final SchoolService schoolService ) {

		this.schoolService = schoolService;
	}


	public Optional< School > get( UUID id ) {

		return schoolService.get( id );
	}

	public School create( School entity ) {

		return schoolService.create( entity );
	}

	public School update( School entity ) {

		return schoolService.update( entity );
	}


	public void delete( UUID id ) {

		schoolService.delete( id );
	}


	public Page< School > list( Pageable pageable ) {

		return schoolService.list( pageable );
	}


	@GetMapping ( "all" )
	public List< School > list() {

		return schoolService.list();
	}


	public List< School > list( final Sort sort ) {

		return schoolService.list( sort );
	}


	public int count() {

		return ( int ) schoolService.count();
	}

}
