package be.intecbrussel.bbjja.views.ninjaschool;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import be.intecbrussel.bbjja.views.home.SlidesView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@PageTitle ( "Ninja School" )
@Route ( value = "ninjasc", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class NinjaSchoolView extends VerticalLayout {

	public NinjaSchoolView( final AuthenticatedUser user ) {

		setSpacing( false );

		final var navLayout = new HorizontalLayout();
		navLayout.setSpacing( false );

		final var offers = new Button( "Manage Offers", onClick -> {
			UI.getCurrent().navigate( SlidesView.class );
		} );

		navLayout.add( offers );
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
