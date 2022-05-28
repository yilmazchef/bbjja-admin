package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;

@PageTitle ( "BBJA | Users | Edit" )
@Route ( value = "users/edit", layout = MainLayout.class )
@PermitAll
public class UsersEditView extends ViewFrame implements LocaleChangeObserver {


	private final AuthenticatedUser authenticatedUser;
	private final UserService userService;


	public UsersEditView( final AuthenticatedUser authenticatedUser, final UserService userService ) {

		this.authenticatedUser = authenticatedUser;
		this.userService = userService;

		setId( "users_edit" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var existingUserData = userService.list();
		final var userEditLayouts = new ArrayList< Component >();

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

			userEditLayouts.add( updateUserLayout );

		}


		final var content = new FlexBoxLayout( userEditLayouts.toArray( Component[] :: new ) );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifyUserDeleted( final User person ) {

		Notification.show(
				String.format( "User with email %s has been removed from the list.", person.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
