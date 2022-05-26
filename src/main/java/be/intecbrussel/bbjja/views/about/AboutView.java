package be.intecbrussel.bbjja.views.about;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@PageTitle ( "About" )
@Route ( value = "about", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class AboutView extends VerticalLayout {

	@Autowired
	public AboutView( final AuthenticatedUser user ) {

		setSpacing( false );

		final var navLayout = new HorizontalLayout();
		navLayout.setPadding( false );
		navLayout.setSpacing( false );

		final var teamsButton = new NativeButton( "Teams" );
		teamsButton.addClickListener( onClick ->
				teamsButton.getUI().ifPresent( ui ->
						ui.navigate( TeamsView.class ) )
		);

		final var partnersButton = new NativeButton( "Partners" );
		partnersButton.addClickListener( onClick ->
				partnersButton.getUI().ifPresent( ui ->
						ui.navigate( PartnersView.class ) )
		);

		navLayout.add( teamsButton, partnersButton );
		navLayout.setSizeFull();
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
