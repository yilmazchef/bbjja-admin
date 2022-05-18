package be.intecbrussel.bbjja.data.generator;


import be.intecbrussel.bbjja.data.Role;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.util.Collections;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
public class MockUserGenerator {

	@Bean
	public CommandLineRunner loadData( PasswordEncoder passwordEncoder, UserRepository userRepository ) {

		return args -> {
			final var logger = LoggerFactory.getLogger( getClass() );

			if ( userRepository.count() != 0L ) {
				logger.info( "Using existing database" );
				return;
			}
			int seed = 123;

			logger.info( "Generating mock data..." );

			final var user = new User();
			user.setName( "John Normal" );
			user.setUsername( "user" );
			user.setHashedPassword( passwordEncoder.encode( "user" ) );
			user.setProfilePictureUrl(
					"https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80" );
			user.setRoles( Collections.singleton( Role.USER ) );
			userRepository.save( user );

			final var editor = new User();
			editor.setName( "Eddy Teur" );
			editor.setUsername( "editor" );
			editor.setHashedPassword( passwordEncoder.encode( "editor" ) );
			editor.setProfilePictureUrl(
					"https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80" );
			editor.setRoles( Set.of( Role.USER, Role.EDITOR ) );
			userRepository.save( editor );

			final var admin = new User();
			admin.setName( "Emma Powerful" );
			admin.setUsername( "admin" );
			admin.setHashedPassword( passwordEncoder.encode( "admin" ) );
			admin.setProfilePictureUrl(
					"https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80" );
			admin.setRoles( Set.of( Role.USER, Role.ADMIN ) );
			userRepository.save( admin );

			logger.info( "Generated mock data with a user, an editor, and an admin." );
		};
	}

}