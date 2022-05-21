package be.intecbrussel.bbjja.views.users;


import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.security.RolesAllowed;

@PageTitle ( "Profile" )
@Route ( value = "users/profile/update", layout = MainLayout.class )
@RolesAllowed ( { "USER", "EDITOR", "ADMIN" } )
public class UpdateProfileLayout extends VerticalLayout {

	public UpdateProfileLayout( final AuthenticatedUser user, final UserService service, final PasswordEncoder encoder ) {

		// tag::snippet[]

		if ( user.get().isPresent() ) {

			final var u = user.get().get();

			final var firstName = new TextField( "First name" );
			firstName.setValue( u.getFirstName() );
			final var lastName = new TextField( "Last name" );
			lastName.setValue( u.getLastName() );
			final var email = new TextField( "Email" );
			email.setValue( u.getEmail() );
			final var username = new TextField( "Username" );
			final var password = new PasswordField( "Password" );
			final var confirmPassword = new PasswordField( "Confirm password" );

			final var submit = new Button( "Submit", onClick -> {
				if ( ! u.getFirstName().equalsIgnoreCase( firstName.getValue() ) ) {
					u.setFirstName( firstName.getValue() );
				}

				if ( ! u.getLastName().equalsIgnoreCase( lastName.getValue() ) ) {
					u.setLastName( lastName.getValue() );
				}

				if ( ! u.getUsername().equalsIgnoreCase( username.getValue() ) ) {
					u.setUsername( username.getValue() );
				}

				if ( ! u.getEmail().equalsIgnoreCase( email.getValue() ) ) {
					u.setEmail( email.getValue() );
				}

				if ( ! u.getHashedPassword().equalsIgnoreCase( encoder.encode( password.getValue() ) ) ) {
					u.setHashedPassword( encoder.encode( password.getValue() ) );
				}

				service.update( u );
			} );

			final var formLayout = new FormLayout();
			formLayout.add( firstName, lastName, username, password, confirmPassword );
			formLayout.setResponsiveSteps(
					// Use one column by default
					new FormLayout.ResponsiveStep( "0", 1 ),
					// Use two columns, if layout's width exceeds 500px
					new FormLayout.ResponsiveStep( "500px", 2 ) );
			// Stretch the username field over 2 columns
			formLayout.setColspan( username, 2 );
			// end::snippet[]

			add( formLayout );
		}

	}

}
