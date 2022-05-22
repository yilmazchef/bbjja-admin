package be.intecbrussel.bbjja.views.sitesettings;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@PageTitle ( "Settings" )
@Route ( value = "settings", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class SettingsView extends VerticalLayout {

	@Autowired
	public SettingsView( final AuthenticatedUser user ) {

		final var navLayout = new HorizontalLayout();
		navLayout.setSpacing( false );

		final var contactsButton = new Button( "Manage Contacts", onClick -> {
			UI.getCurrent().navigate( ContactView.class );
		} );

		final var usersButton = new Button( "Manage Users", onClick -> {
			UI.getCurrent().navigate( UsersView.class );
		} );

		navLayout.add( contactsButton, usersButton );
		navLayout.setSizeFull();
		navLayout.setAlignItems( Alignment.STRETCH );
		navLayout.getStyle().set( "text-align", "center" );

		add( navLayout );

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
