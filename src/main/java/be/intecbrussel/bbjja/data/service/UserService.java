package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository repository;


	@Autowired
	public UserService( UserRepository repository ) {

		this.repository = repository;
	}


	public Optional< User > get( UUID id ) {

		return repository.findById( id );
	}


	public User update( User entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< User > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< User > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
