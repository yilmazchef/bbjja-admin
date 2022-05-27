package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Grappling;
import be.intecbrussel.bbjja.data.entity.Grappling.GrapplingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GrapplingService {

	private final GrapplingRepository repository;


	@Autowired
	public GrapplingService( final GrapplingRepository repository ) {

		this.repository = repository;
	}


	public Optional< Grappling > get( UUID id ) {

		return repository.findById( id );
	}


	public Grappling create( Grappling entity ) {

		return repository.save( entity );
	}


	public Grappling update( Grappling entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< Grappling > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Grappling > list() {

		return repository.findAll();
	}


	public List< Grappling > school() {

		return repository.findByType( GrapplingType.SCHOOL );
	}


	public List< Grappling > street() {

		return repository.findByType( GrapplingType.STREET );
	}


	public List< Grappling > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
