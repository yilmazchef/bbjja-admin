package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.exceptions.UserServiceException;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;

	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public UserService( final UserRepository repository, final PasswordEncoder encoder, final AuthenticatedUser authenticatedUser ) {

		this.repository = repository;
		this.encoder = encoder;
		this.authenticatedUser = authenticatedUser;
	}


	public Optional< User > get( UUID id ) {

		return repository.findById( id );
	}


	public User create( User entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return repository.save( entity );
	}


	public User update( User entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setModifiedBy( u.getUsername() );
		} );

		return repository.save( entity );
	}


	public User changePassword( final @NotEmpty String username, final @NotEmpty String newPassword ) throws UserServiceException {

		final var foundUser = repository.findByUsername( username );
		if ( foundUser == null ) {
			throw UserServiceException.notFound();
		}

		foundUser.setHashedPassword( newPassword );
		return update( foundUser );
	}


	public User changePassword( final @NotEmpty String username, final @NotEmpty String oldPassword, final @NotEmpty String newPassword ) throws UserServiceException {

		if ( oldPassword.equals( newPassword ) ) {
			throw UserServiceException.newPasswordRequired();
		}

		final var foundUser = repository.findByUsername( username );
		if ( foundUser == null ) {
			throw UserServiceException.notFound();
		}

		foundUser.setHashedPassword( newPassword );
		return update( foundUser );
	}


	public void delete( UUID id ) {

		repository.deleteById( id );
	}


	public Page< User > list( Pageable pageable ) {

		return repository.findAll( pageable );
	}


	public List< User > list() {

		return repository.findAll();
	}


	public List< User > list( final Sort sort ) {

		return repository.findAll( sort );
	}


	public int count() {

		return ( int ) repository.count();
	}

}
