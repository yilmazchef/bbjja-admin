package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

import static java.lang.System.out;

@SpringComponent
@Tag ( "slides-view-layout" )
public class SlidesViewLayout extends VerticalLayout implements LocaleChangeObserver {

	@Autowired
	public SlidesViewLayout( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {


		if ( slideService.count() > 0 ) {
			final var slides = slideService.list( Sort.by( "dateModified" ) );
			final var layouts = new ArrayList< VerticalLayout >();

			for ( final var s : slides ) {

				final var layout = new VerticalLayout();
				final var image = new Image( s.getImageUrl(), "BBJA Slide Image" );
				out.println( s.getImageUrl() );
				image.setWidthFull();

				final var title = new TextField( "Title" );
				title.setValue( s.getTitle() );
				title.setRequiredIndicatorVisible( true );
				title.setRequired( true );
				title.setWidthFull();

				final var description = new TextField( "Page slug" );
				description.setValue( s.getPage().getSlug() );
				description.setWidthFull();

				layout.add( image, title, description );

				layouts.add( layout );

			}

			for ( final var l : layouts ) {
				add( l );
			}
		}


	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
