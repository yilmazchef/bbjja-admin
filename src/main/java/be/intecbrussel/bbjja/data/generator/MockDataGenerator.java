package be.intecbrussel.bbjja.data.generator;


import be.intecbrussel.bbjja.data.Role;
import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.PageRepository;
import be.intecbrussel.bbjja.data.service.SlideRepository;
import be.intecbrussel.bbjja.data.service.SubscriberRepository;
import be.intecbrussel.bbjja.data.service.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.util.Collections;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
public class MockDataGenerator {

	@Bean
	public CommandLineRunner loadData( PasswordEncoder passwordEncoder,
	                                   UserRepository userRepository, SlideRepository slideRepository,
	                                   PageRepository pageRepository, SubscriberRepository subscriberRepository ) {

		return args -> {
			final var logger = LoggerFactory.getLogger( getClass() );

			if ( userRepository.count() != 0L ) {
				logger.info( "Using existing database" );
				return;
			}

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


			final var homePage = new Page()
					.withTitle( "Home page title! " )
					.withDescription( "Home page description here..." );

			final var savedHomePage = pageRepository.save( homePage );

			for ( int index = 0; index < 10; index++ ) {

				final var newSlide = new Slide()
						.withImageUrl( "https://localhost:8080/images/empty-plant.png" )
						.withTitle( "Slide 01" );

				newSlide.setPage( savedHomePage );

				final var savedSlide = slideRepository.save( newSlide );
				logger.info( String.format( "A new slide is created with %s", savedSlide.getId() ) );

			}

			for ( int index = 0; index < 10; index++ ) {
				final var subscriber = new Subscriber()
						.withEmail( String.format( "subs%d@cribe.com", index ) )
						.withFirstName( String.format( "SubFirst %s", index ) )
						.withLastName( String.format( "SubLast %s", index ) );

				final Subscriber savedSubscriber = subscriberRepository.save( subscriber );
				homePage.addSubscriber( savedSubscriber );
				logger.info( String.format( "A user with an email address ' %s ' has subscribed to the website.", savedSubscriber ) );

			}


			logger.info( "Generated mock data with a user, an editor, and an admin." );
		};
	}

}