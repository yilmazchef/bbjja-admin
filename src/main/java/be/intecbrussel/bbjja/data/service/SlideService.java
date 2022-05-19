package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Slide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SlideService {

	private final SlideRepository repository;


	@Autowired
	public SlideService( final SlideRepository repository ) {

		this.repository = repository;
	}


	public Optional< Slide > get( UUID id ) {

		return repository.findById( id );
	}


	public Slide update( Slide entity ) {

		return repository.save( entity );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< Slide > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< Slide > list() {

		return repository.findAll();
	}


	public List< Slide > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
