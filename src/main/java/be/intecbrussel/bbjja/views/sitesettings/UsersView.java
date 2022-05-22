package be.intecbrussel.bbjja.views.sitesettings;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.mappers.UserMapper;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@PageTitle ( "Users" )
@Route ( value = "users/admin", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class UsersView extends VerticalLayout {

	@Autowired
	public UsersView( final AuthenticatedUser user, final UserService service, final UserMapper mapper ) {

		// setSpacing( false );


		final List< User > users = service.list();
		for ( final User u : users ) {

			final var username = new TextField( "Username" );
			username.setValue( u.getUsername() );
			final var newPassword = new PasswordField( "New Password" );
			final var confirmPassword = new PasswordField( "Confirm password" );

			final var formLayout = new FormLayout();
			formLayout.add( username, newPassword, confirmPassword );
			formLayout.setResponsiveSteps(
					// Use one column by default
					new FormLayout.ResponsiveStep( "0", 1 ),
					// Use two columns, if layout's width exceeds 500px
					new FormLayout.ResponsiveStep( "500px", 2 ) );
			// Stretch the username field over 2 columns
			formLayout.setColspan( username, 2 );

			final var submit = new Button( "Submit", onClick -> {
				final var savedUser = service.changePassword( username.getValue(), confirmPassword.getValue() );
				new Notification( savedUser.toString(), 3000 ).open();
			} );

			add( formLayout );
			add( submit );
		}

		notifyAuthenticatedUser( user );


		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}


	private void notifyAuthenticatedUser( final AuthenticatedUser user ) {

		final Optional< User > oUser = user.get();
		oUser.ifPresent( u -> {

			new Notification( u.getUsername() + " is logged in.." ).open();
		} );
	}

}
