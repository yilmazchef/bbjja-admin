package be.intecbrussel.bbjja.data.generator;


import be.intecbrussel.bbjja.data.entity.*;
import be.intecbrussel.bbjja.data.service.*;
import com.github.javafaker.Faker;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@SpringComponent
public class MockDataGenerator {

	@Bean
	public CommandLineRunner loadData( PasswordEncoder passwordEncoder,
	                                   UserRepository userRepository, EmployeeRepository employeeRepository, RoleRepository roleRepository,
	                                   PageRepository pageRepository, SubscriberRepository subscriberRepository,
	                                   OfferRepository offerRepository, SchoolRepository schoolRepository,
	                                   SlideRepository slideRepository,
	                                   MockPhotoGenerator photoGenerator ) {

		return args -> {
			final var logger = LoggerFactory.getLogger( getClass() );

			final var photos = photoGenerator.fetchLocal();
			final var faker = new Faker( Locale.getDefault() );

			Thread.sleep( 5000 );

			if ( userRepository.count() != 0L ) {
				logger.info( "Using existing database" );
				return;
			}

			logger.info( "Generating mock data..." );

			final var userRole = new Role()
					.setTitle( "User" )
					.setDescription( "Has LIMITED access to all services including UI and API." )
					.setParent( null )
					.setMaxAllowedUsers( 10 );

			final var editorRole = new Role()
					.setTitle( "Editor" )
					.setDescription( "Has full access to all services from UI." )
					.setParent( userRole )
					.setMaxAllowedUsers( 10 );

			final var adminRole = new Role()
					.setTitle( "Administrator" )
					.setDescription( "Has full access to all services including UI and API." )
					.setParent( editorRole )
					.setMaxAllowedUsers( 10 );

			roleRepository.saveAll( List.of( adminRole, editorRole, userRole ) );


			final var emailUser = faker.internet().emailAddress();
			final var usernameUser = "user";
			final var user = new User()
					.setFirstName( faker.name().firstName() )
					.setLastName( faker.name().lastName() )
					.setEmail( emailUser )
					.setUsername( usernameUser )
					.setHashedPassword( passwordEncoder.encode( "user" ) )
					.setProfilePictureUrl(
							"https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80" )
					.setRoles( Collections.singleton( Role.USER ) );

			if ( ! userRepository.existsByUsernameOrEmail( usernameUser, emailUser ) ) {
				userRepository.save( user );
			}

			final var emailEditor = faker.internet().emailAddress();
			final var usernameEditor = "editor";
			final var editor = new User()
					.setFirstName( faker.name().firstName() )
					.setLastName( faker.name().lastName() )
					.setEmail( emailEditor )
					.setUsername( usernameEditor )
					.setHashedPassword( passwordEncoder.encode( "editor" ) )
					.setProfilePictureUrl(
							"https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80" )
					.setRoles( Set.of( Role.USER, Role.EDITOR ) );

			if ( ! userRepository.existsByUsernameOrEmail( usernameEditor, emailEditor ) ) {
				userRepository.save( editor );
			}

			final var emailAdmin = faker.internet().emailAddress();
			final var usernameAdmin = "admin";
			final var admin = new User()
					.setFirstName( faker.name().firstName() )
					.setLastName( faker.name().lastName() )
					.setEmail( emailAdmin )
					.setUsername( usernameAdmin )
					.setHashedPassword( passwordEncoder.encode( "admin" ) )
					.setProfilePictureUrl(
							"https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80" )
					.setRoles( Set.of( Role.USER, Role.EDITOR, Role.ADMIN ) );

			if ( ! userRepository.existsByUsernameOrEmail( usernameAdmin, emailAdmin ) ) {
				userRepository.save( admin );
			}


			final var homePage = new Page()
					.setTitle( "Home page title! " )
					.setSlug( String.format( "https://www.%s.com/", faker.internet().domainName() ) )
					.setDescription( faker.lorem().paragraph() );

			final var savedHomePage = pageRepository.save( homePage );

			for ( int index = 0; index < 5; index++ ) {

				final var newSlide = new Slide()
						.setImageUrl( photos[ index ].getUrl() )
						.setTitle( faker.ancient().hero() );

				newSlide.setPage( savedHomePage );

				final var savedSlide = slideRepository.save( newSlide );
				logger.info( String.format( "A new slide is created with %s", savedSlide.getId() ) );

			}

			for ( int index = 0; index < 200; index++ ) {
				final var subscriber = new Subscriber()
						.setEmail( faker.internet().emailAddress() )
						.setFirstName( faker.name().firstName() )
						.setLastName( faker.name().lastName() );

				final Subscriber savedSubscriber = subscriberRepository.save( subscriber );
				homePage.getSubscribers().add( savedSubscriber );
				logger.info( String.format( "A user with an email address ' %s ' has subscribed to the website.", savedSubscriber ) );

			}

			final var contactPage = new Page()
					.setTitle( "Contact page title! " )
					.setSlug( "contact01" )
					.setDescription( faker.lorem().paragraph() );

			final var savedContactPage = pageRepository.save( contactPage );

			final var ninjaSchoolPage = new Page()
					.setTitle( "Ninja school page title! " )
					.setSlug( "ninjaSchool01" )
					.setDescription( "Ninja school page description here..." );

			final var savedNinjaSchoolPage = pageRepository.save( ninjaSchoolPage );

			for ( int index = 0; index < 4; index++ ) {
				final var offer = new Offer();
				offer.setTitle( String.format( "Ninja school offer title %d", index ) );
				offer.setDescription( String.format( "Ninja school offer description: %d", index ) );
				offer.setForwardUrl( String.format( "https://www.bbjja.be/offers/%d", index ) );
				offer.setPage( ninjaSchoolPage );

				final var savedOffer = offerRepository.save( offer );

				logger.info( String.format( "A new offer is created with title: %s and url: %s", savedOffer.getTitle(), savedOffer.getForwardUrl() ) );

				for ( int subIndex = 0; subIndex < 5; subIndex++ ) {
					final var schoolOffered = new School();
					schoolOffered.setTitle( String.format( "Ninja school title %s offered school title %d", index, subIndex ) );
					schoolOffered.setPhone( "+32455611509" );
					schoolOffered.setLatitude( 50.85994129672338F );
					schoolOffered.setLongitude( 4.3374293534765815F );
					schoolOffered.setIframe( "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2518.4537430783535!2d4.334736415506741!3d50.85979907953399!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c3c392bd96d221%3A0x5d49a8f080c28411!2sBrussel%20Brazilian%20Jiu-Jitsu%20Academy!5e0!3m2!1sen!2sbe!4v1653630213331!5m2!1sen!2sbe\" width=\"800\" height=\"600\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>" );

					schoolOffered.setOffer( savedOffer );

					final School savedSchoolOffered = schoolRepository.save( schoolOffered );

					logger.info( String.format( "A new offer is created with title: %s and coordinates: %s, %s", savedSchoolOffered.getTitle(), savedSchoolOffered.getLatitude(), savedSchoolOffered.getLongitude() ) );

				}

			}

			final var aboutPage = new Page()
					.setTitle( "About page title! " )
					.setSlug( "about-team-partners" )
					.setDescription( faker.lorem().paragraph() );

			final var savedAboutPage = pageRepository.save( aboutPage );

			for ( int index = 0; index < 6; index++ ) {
				final var employee = new Employee()
						.setEmail( faker.internet().emailAddress() )
						.setFirstName( faker.name().firstName() )
						.setLastName( faker.name().lastName() )
						.setJobTitle( faker.job().title() )
						.setProfilePictureUrl(
								"https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80"
						);

				final var savedEmployee = employeeRepository.save( employee );
				logger.info( String.format( "A new member, %s %s with an email address %s has been added to BBJJA team.",
						savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getEmail() ) );

			}

			logger.info( "Generated mock data with a user, an editor, and an admin." );
		};
	}

}