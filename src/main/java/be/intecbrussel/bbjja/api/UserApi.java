package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.exceptions.UserServiceException;
import be.intecbrussel.bbjja.data.service.UserService;
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
public class UserApi {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	private final AuthenticatedUser authenticatedUser;


	@Autowired
	public UserApi( final UserService userService, final PasswordEncoder passwordEncoder, final AuthenticatedUser authenticatedUser ) {

		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authenticatedUser = authenticatedUser;
	}


	public Optional< User > get( UUID id ) {

		return userService.get( id );
	}


	public User create( User entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return userService.create( entity );
	}


	public User update( User entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setModifiedBy( u.getUsername() );
		} );

		return userService.create( entity );
	}


	public User changePassword( final @NotEmpty String username, final @NotEmpty String newPassword ) throws UserServiceException {

		return userService.changePassword( username, newPassword );
	}


	public User changePassword( final @NotEmpty String username, final @NotEmpty String oldPassword, final @NotEmpty String newPassword ) throws UserServiceException {

		return userService.changePassword( username, oldPassword, newPassword );
	}


	public void delete( UUID id ) {

		userService.delete( id );
	}


	public Page< User > list( Pageable pageable ) {

		return userService.list( pageable );
	}


	public List< User > list() {

		return userService.list();
	}


	public List< User > list( final Sort sort ) {

		return userService.list( sort );
	}


	public int count() {

		return ( int ) userService.count();
	}

}
