package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.dto.NewPageRequest;
import be.intecbrussel.bbjja.data.dto.PageResponse;
import be.intecbrussel.bbjja.data.dto.UpdatePageRequest;
import be.intecbrussel.bbjja.data.mappers.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PageService {

	private final PageRepository repository;
	private final PageMapper mapper;


	@Autowired
	public PageService( final PageRepository repository, final PageMapper mapper ) {

		this.repository = repository;
		this.mapper = mapper;
	}


	public Optional< PageResponse > get( UUID id ) {

		return repository
				.findById( id )
				.map( mapper :: pageToPageResponse );
	}


	public PageResponse create( NewPageRequest newPageRequest ) {

		return mapper
				.pageToPageResponse(
						repository.save(
								mapper.newPageRequestToPage( newPageRequest )
						)
				);
	}


	public PageResponse update( UpdatePageRequest updatePageRequest ) {

		return mapper.pageToPageResponse( repository.save(
				mapper.updatePageRequestToPage( updatePageRequest )
		) );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public List< PageResponse > list( Pageable pageable ) {

		return repository
				.findAll( pageable )
				.stream()
				.map( mapper :: pageToPageResponse )
				.collect( Collectors.toUnmodifiableList() );
	}


	public List< PageResponse > list() {

		return repository
				.findAll()
				.stream()
				.map( mapper :: pageToPageResponse )
				.collect( Collectors.toUnmodifiableList() );
	}


	public List< PageResponse > list( final Sort sort ) {

		return repository
				.findAll( sort )
				.stream()
				.map( mapper :: pageToPageResponse )
				.collect( Collectors.toUnmodifiableList() );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
