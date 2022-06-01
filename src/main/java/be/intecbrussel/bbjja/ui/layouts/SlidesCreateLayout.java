package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@Tag ( "slides-create-layout" )
public class SlidesCreateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SlidesCreateLayout( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {


		final var newSlideLayout = new VerticalLayout();

		final var newImageTitle = new TextField( "Title" );
		newImageTitle.setWidthFull();
		final var newImageURLField = new TextField( "Image URL" );
		newImageURLField.setWidthFull();
		final var newImageButton = new Button( "Add new slide", onClick -> {
			if ( ! newImageURLField.getValue().isEmpty() ) {
				final var newSlide = ( Slide ) new Slide().setImageUrl( newImageURLField.getValue() ).setTitle( newImageTitle.getValue() ).setIsActive( Boolean.TRUE );
				final var createdSlide = slideService.create( newSlide );
				notifySlideCreated( newSlide.getImageUrl() );
			}
		} );
		newImageButton.setWidthFull();
		newSlideLayout.addAndExpand( newImageTitle, newImageURLField, newImageButton );

		add( newSlideLayout );

	}


	private void notifySlideCreated( final String newImageURL ) {

		new Notification( String.format( "Slide image URL is added from %s", newImageURL ) ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
