package be.intecbrussel.bbjja.views.home;


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

import static java.lang.System.out;

@PageTitle ( "Slides" )
@Route ( value = "slides", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class SlidesView extends VerticalLayout {

	@Autowired
	public SlidesView( final AuthenticatedUser user, final SlideService service ) {

		final var slidesData = service.list();
		final var slidesLayout = new VerticalLayout();

		for ( final var s : slidesData ) {
			final var slideItemLayout = new VerticalLayout();
			final var slideItemImg = new Image( s.getImageUrl(), "Example Slide Image" );
			out.println( s.getImageUrl() );
			slideItemImg.setWidth( "80%" );
			final var slideItemH = new H2( s.getTitle() );
			final var slideItemP = new Paragraph( "Slide description, slogan, message, detailed content etc. is written here. 🤗" );
			slideItemLayout.add( slideItemImg, slideItemH, slideItemP );
			slidesLayout.addAndExpand( slideItemLayout );
		}

		add( slidesLayout );

		notifyAuthenticatedUser( user );

	}


	private void notifyAuthenticatedUser( final AuthenticatedUser user ) {

		final Optional< User > oUser = user.get();
		oUser.ifPresent( u -> {

			new Notification( u.getUsername() + " is logged in.." ).open();
		} );
	}

}
