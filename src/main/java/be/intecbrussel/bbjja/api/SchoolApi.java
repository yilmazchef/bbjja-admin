package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.School;
import be.intecbrussel.bbjja.data.service.SchoolService;
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
@RequestMapping ( value = "api/schools_offered" )
@PermitAll
public class SchoolApi {

	private final SchoolService repository;


	@Autowired
	public SchoolApi( final SchoolService repository ) {

		this.repository = repository;
	}


	public Optional< School > get( UUID id ) {

		return repository.get( id );
	}

	public School create( School entity ) {

		return repository.create( entity );
	}

	public School update( School entity ) {

		return repository.update( entity );
	}


	public void delete( UUID id ) {

		repository.delete( id );
	}


	public Page< School > list( Pageable pageable ) {

		return repository.list( pageable );
	}


	public List< School > list() {

		return repository.list();
	}


	public List< School > list( final Sort sort ) {

		return repository.list( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
