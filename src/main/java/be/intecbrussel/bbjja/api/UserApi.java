package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.exceptions.UserServiceException;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = EndPoints.USER_CLASS_LEVEL )
@PermitAll
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


	@GetMapping ( EndPoints.USER_GET_BY_ID )
	public Optional< User > get( @PathVariable @NotNull final UUID id ) {

		return userService.get( id );
	}


	@PostMapping ( EndPoints.USERS_CREATE )
	public User create( @RequestBody @Valid final User entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setCreatedBy( u.getUsername() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return userService.create( entity );
	}


	@PutMapping ( EndPoints.USER_UPDATE_BY_EXAMPLE )
	public User update( @RequestBody @Valid final User entity ) {

		final Optional< User > oUser = authenticatedUser.get();
		oUser.ifPresent( u -> {
			entity.setDateModified( LocalDateTime.now() );
			entity.setModifiedBy( u.getUsername() );
		} );

		return userService.create( entity );
	}


	@PatchMapping ( EndPoints.USER_CHANGE_PASSWORD_NO_OLD )
	public User changePassword( final @NotEmpty String username, final @NotEmpty String newPassword ) throws UserServiceException {

		return userService.changePassword( username, newPassword );
	}


	@PatchMapping ( EndPoints.USER_CHANGE_PASSWORD_WITH_VALIDATION )
	public User changePassword( final @NotEmpty String username, final @NotEmpty String oldPassword, final @NotEmpty String newPassword ) throws UserServiceException {

		return userService.changePassword( username, oldPassword, newPassword );
	}


	@DeleteMapping ( EndPoints.USER_DELETE_BY_ID )
	public void delete( @PathVariable @NotNull final UUID id ) {

		userService.delete( id );
	}


	@GetMapping ( EndPoints.USERS_LIST_IN_PAGES )
	public Page< User > list( @PathVariable @NotNull @PositiveOrZero final Integer page ) {

		return userService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.USER_LIST_ALL )
	public List< User > list() {

		return userService.list();
	}


	@GetMapping ( EndPoints.USERS_LIST_SORTED )
	public List< User > list( @RequestBody @Valid final Sort sort ) {

		return userService.list( sort );
	}


	@GetMapping ( EndPoints.USERS_COUNT )
	public int count() {

		return userService.count();
	}

}
