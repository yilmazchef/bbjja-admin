package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.Role;
import be.intecbrussel.bbjja.data.dto.NewUserRequest;
import be.intecbrussel.bbjja.data.dto.UpdateUserRequest;
import be.intecbrussel.bbjja.data.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

	public User toNewEntity( final NewUserRequest dto ) {

		return new User()
				.withUsername( Objects.requireNonNull( dto.getUsername() ) )
				.withHashedPassword( Objects.requireNonNull( dto.getHashedPassword() ) )
				.withRole( Role.USER ); // NEW USERS WILL HAVE Role.USER AS DEFAULT
	}


	public User toUpdateEntity( final UpdateUserRequest dto ) {

		// UPDATED USERS WILL HAVE NO CHANGE IN THEIR ROLES
		return new User()
				.withName( Objects.requireNonNull( dto.getName() ) )
				.withUsername( Objects.requireNonNull( dto.getUsername() ) )
				.withHashedPassword( Objects.requireNonNull( dto.getHashedPassword() ) )
				.withProfilePictureUrl( dto.getProfilePictureUrl() );
	}


	public NewUserRequest toNewDTO( final User entity ) {

		return new NewUserRequest()
				.withUsername( Objects.requireNonNull( entity.getUsername() ) );
	}


	public UpdateUserRequest toUpdateDTO( final User entity ) {

		return new UpdateUserRequest()
				.withId( Objects.requireNonNull( entity.getId() ) )
				.withName( Objects.requireNonNull( entity.getName() ) )
				.withUsername( Objects.requireNonNull( entity.getUsername() ) )
				.withProfilePictureUrl( Objects.requireNonNull( entity.getProfilePictureUrl() ) );
	}


}
