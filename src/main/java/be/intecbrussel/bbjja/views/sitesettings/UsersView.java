package be.intecbrussel.bbjja.views.sitesettings;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@PageTitle ( "Users" )
@Route ( value = "users/admin", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class UsersView extends VerticalLayout {

	@Autowired
	public UsersView( final AuthenticatedUser authenticatedUser, final UserService userService ) {

		final var accordion = new Accordion();
		accordion.setSizeFull();

		final var existingUserData = userService.list();

		final var usersGrid = new Grid<>( User.class, false );
		usersGrid.setAllRowsVisible( true );
		usersGrid.addColumn( User :: getFirstName ).setHeader( "First Name" );
		usersGrid.addColumn( User :: getLastName ).setHeader( "Last Name" );
		usersGrid.addColumn( User :: getEmail ).setHeader( "Email" );
		usersGrid.addColumn(
				new ComponentRenderer<>( Button :: new, ( b, u ) -> {

					b.setIcon( new Icon( VaadinIcon.TRASH ) );
					b.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
					b.addClickListener( onDelete -> {

						if ( authenticatedUser == null ) {
							return;
						}

						userService.delete( u.getId() );
						existingUserData.remove( u );

						if ( existingUserData.size() > 0 ) {
							usersGrid.setVisible( true );
							usersGrid.getDataProvider().refreshAll();
						} else {
							usersGrid.setVisible( false );
						}

						usersGrid.setItems( existingUserData );
						notifyUserDeleted( u );

					} );


				} ) ).setHeader( "Manage" );

		usersGrid.setItems( existingUserData );

		accordion.add( "View/Delete Users", usersGrid );

		for ( final User existingUserItem : existingUserData ) {

			final var usernameField = new TextField( "Username" );
			usernameField.setValue( existingUserItem.getUsername() );
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

			accordion.add( String.format( "Change %s 's password", existingUserItem.getUsername() ), updateUserLayout );
		}

		add( accordion );

		notifyAuthenticatedUser( authenticatedUser );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}


	private void notifyAuthenticatedUser( final AuthenticatedUser user ) {

		final Optional< User > oUser = user.get();
		oUser.ifPresent( u -> {

			new Notification( u.getUsername() + " is logged in..", 1000, Notification.Position.BOTTOM_CENTER ).open();
		} );
	}


	private void notifyUserDeleted( final User person ) {

		Notification.show(
				String.format( "User with email %s has been removed from the list.", person.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}

}
