package be.intecbrussel.bbjja.views.home;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
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

		final var slidesData = service.list();

		final var slidesGrid = new Grid<>( Slide.class, true );
		slidesGrid.setItems( slidesData );

//		for ( final Slide s : slidesData ) {
//			final var slideItemLayout = new VerticalLayout();
//			final var slideItemImg = new Image( "images/empty-plant.png", "Example Slide Image" );
//			slideItemImg.setWidth( 400F, Unit.PIXELS );
//			final var slideItemH = new H2( "Slide header 01" );
//			final var slideItemP = new Paragraph( "Slide description, slogan, message, detailed content etc. is written here. 🤗" );
//			slideItemLayout.add( slideItemImg, slideItemH, slideItemP );
//			slidesLayout.add( slideItemLayout );
//		}

		add( slidesGrid );

		notifyAuthenticatedUser( user );

	}


	private void notifyAuthenticatedUser( final AuthenticatedUser user ) {

		final Optional< User > oUser = user.get();
		oUser.ifPresent( u -> {

			new Notification( u.getUsername() + " is logged in.." ).open();
		} );
	}

}
