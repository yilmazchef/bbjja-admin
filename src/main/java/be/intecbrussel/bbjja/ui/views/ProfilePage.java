package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.security.PermitAll;
import java.time.Instant;

@PageTitle ( "BBJJA | Profile" )
@Route ( value = "profile", layout = MainLayout.class )
@Tag ( "profile-page" )
@PermitAll
public class ProfilePage extends VerticalLayout {

	@Autowired
	public ProfilePage( final AuthenticatedUser user, final UserService service, final PasswordEncoder encoder ) {

		setId( "profile-page".concat( String.valueOf( Instant.now().getNano() ) ) );

		// tag::snippet[]

		if ( user.get().isPresent() ) {

			final var u = user.get().get();

			final var firstNameField = new TextField( "First name" );
			firstNameField.setValue( u.getFirstName() );
			final var lastNameField = new TextField( "Last name" );
			lastNameField.setValue( u.getLastName() );
			final var emailField = new TextField( "Email" );
			emailField.setValue( u.getEmail() );
			final var usernameField = new TextField( "Username" );
			usernameField.setValue( u.getUsername() );
			final var passwordField = new PasswordField( "Password" );
			final var confirmPasswordField = new PasswordField( "Confirm password" );

			final var updateProfileButton = new Button( "Update profile", onClick -> {
				if ( ! u.getFirstName().equalsIgnoreCase( firstNameField.getValue() ) ) {
					u.setFirstName( firstNameField.getValue() );
				}

				if ( ! u.getLastName().equalsIgnoreCase( lastNameField.getValue() ) ) {
					u.setLastName( lastNameField.getValue() );
				}

				if ( ! u.getUsername().equalsIgnoreCase( usernameField.getValue() ) ) {
					u.setUsername( usernameField.getValue() );
				}

				if ( ! u.getEmail().equalsIgnoreCase( emailField.getValue() ) ) {
					u.setEmail( emailField.getValue() );
				}

				if ( ! u.getHashedPassword().equalsIgnoreCase( encoder.encode( passwordField.getValue() ) ) ) {
					u.setHashedPassword( encoder.encode( passwordField.getValue() ) );
				}

				service.update( u );
			} );

			final var formLayout = new FormLayout();
			formLayout.add(
					firstNameField, lastNameField, usernameField, emailField,
					passwordField, confirmPasswordField,
					updateProfileButton
			);

			formLayout.setResponsiveSteps(
					// Use one column by default
					new FormLayout.ResponsiveStep( "0", 1 ),
					// Use two columns, if layout's width exceeds 500px
					new FormLayout.ResponsiveStep( "500px", 2 ) );
			// end::snippet[]

			add( formLayout );
		}

	}

}
