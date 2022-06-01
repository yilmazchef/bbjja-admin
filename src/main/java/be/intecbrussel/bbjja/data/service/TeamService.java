package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

	private final EmployeeRepository repository;


	@Autowired
	public TeamService( final EmployeeRepository repository ) {

		this.repository = repository;
	}


	public Optional< Team > get( UUID id ) {

		return repository.findById( id );
	}


	public Optional< Team > get( final String email ) {

		return repository.findByEmailIgnoreCase( email );
	}

	public Team create( Team entity ) {

		return repository.save( entity );
	}


	public Team update( Team entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< Team > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Team > list() {

		return repository.findAll();
	}


	public List< Team > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
