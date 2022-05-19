package be.intecbrussel.bbjja.views.home;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@PageTitle ( "Slides" )
@Route ( value = "slides", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class SlidesView extends VerticalLayout {

	@Autowired
	public SlidesView( final AuthenticatedUser user, final SlideService service ) {

		setSpacing( false );


		final var slidesLayout = new VerticalLayout();
		final var slidesData = service.list();

		for ( final Slide s : slidesData ) {
			final var slideItemLayout = new VerticalLayout();
			final var slideItemImg = new Image( "images/empty-plant.png", "placeholder plant" );
			slideItemImg.setWidth( "200px" );
			final var slideItemH = new H2( "This place intentionally left empty" );
			final var slideItemP = new Paragraph( "It’s a place where you can grow your own UI 🤗" );
			slideItemLayout.add( slideItemImg, slideItemH, slideItemP );
			slidesLayout.add( slideItemLayout );
		}

		add( slidesLayout );

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
