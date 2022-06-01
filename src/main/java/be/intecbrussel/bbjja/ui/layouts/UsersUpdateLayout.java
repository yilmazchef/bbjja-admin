package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;

@SpringComponent
@Tag ( "users-update-layout" )
public class UsersUpdateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public UsersUpdateLayout( final AuthenticatedUser authenticatedUser, final UserService userService ) {

		setId( "users-update-layout".concat( String.valueOf( Instant.now().getNano() ) ) );


		final var users = userService.list();
		final var components = new ArrayList< Component >();

		for ( final User u : users ) {

			final var usernameField = new TextField( "Username" );
			usernameField.setValue( u.getUsername() );
			final var newPasswordField = new PasswordField( "New Password" );
			final var confirmPasswordField = new PasswordField( "Confirm password" );

			final var updateUserLayout = new FormLayout();
			updateUserLayout.add( usernameField, newPasswordField, confirmPasswordField );
			updateUserLayout.setResponsiveSteps(
					// Use one column by default
					new FormLayout.ResponsiveStep( "0", 1 ),
					// Use two columns, if layout's width exceeds 500px
					new FormLayout.ResponsiveStep( "500px", 2 ) );
			// Stretch the username field over 2 columns
			updateUserLayout.setColspan( usernameField, 2 );

			final var updateUserButton = new Button( "Submit", onClick -> {
				final var savedUser = userService.changePassword( usernameField.getValue(), confirmPasswordField.getValue() );
				new Notification( savedUser.toString(), 3000 ).open();
			} );

			updateUserLayout.add( updateUserButton );

			components.add( updateUserLayout );

		}
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
