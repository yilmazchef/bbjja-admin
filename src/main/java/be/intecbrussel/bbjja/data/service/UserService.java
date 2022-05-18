package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import be.intecbrussel.bbjja.data.exceptions.UserServiceException;
import be.intecbrussel.bbjja.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository repository;
	private final UserMapper mapper;
	private final PasswordEncoder encoder;


	@Autowired
	public UserService( final UserRepository repository, final UserMapper mapper, final PasswordEncoder encoder ) {

		this.repository = repository;
		this.mapper = mapper;
		this.encoder = encoder;
	}


	public Optional< User > get( UUID id ) {

		return repository.findById( id );
	}


	public User update( User entity ) {

		return repository.save( entity );
	}


	public User changePassword( final String username, final String oldPassword, final String newPassword ) throws UserServiceException {

		if ( username == null ) {
			throw UserServiceException.usernameRequired();
		}

		if ( oldPassword == null ) {
			throw UserServiceException.oldPasswordRequired();
		}

		if ( newPassword == null ) {
			throw UserServiceException.newPasswordRequired();
		}

		final var foundUser = repository.findByUsername( username );

		if ( foundUser == null ) {
			throw UserServiceException.notFound();
		}

		if ( ! foundUser.getHashedPassword().equals( encoder.encode( oldPassword ) ) ) {
			throw UserServiceException.passwordIncorrect();
		}

		foundUser.setHashedPassword( newPassword );
		return repository.save( foundUser );
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
