package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

	private final EmployeeRepository repository;


	@Autowired
	public EmployeeService( final EmployeeRepository repository ) {

		this.repository = repository;
	}


	public Optional< Employee > get( UUID id ) {

		return repository.findById( id );
	}


	public Optional< Employee > get( final String email ) {

		return repository.findByEmailIgnoreCase( email );
	}

	public Employee create( Employee entity ) {

		return repository.save( entity );
	}


	public Employee update( Employee entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< Employee > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Employee > list() {

		return repository.findAll();
	}


	public List< Employee > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
