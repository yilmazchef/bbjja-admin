package be.intecbrussel.bbjja.views.home;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

import static java.lang.System.out;

@PageTitle ( "Slides" )
@Route ( value = "slides", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class SlidesView extends VerticalLayout {

	@Autowired
	public SlidesView( final AuthenticatedUser user, final SlideService service ) {

		final var accordion = new Accordion();
		accordion.setWidthFull();
		accordion.setHeightFull();

		final var newSlideLayout = new VerticalLayout();
		newSlideLayout.setSpacing( false );
		newSlideLayout.setPadding( false );

		final var newImageTitle = new TextField( "Image Title" );
		newImageTitle.setWidthFull();
		final var newImageURLField = new TextField( "Image URL" );
		newImageURLField.setWidthFull();
		final var newImageButton = new Button( "Add new slide", onClick -> {
			if ( ! newImageURLField.getValue().isEmpty() ) {
				final var newSlide = ( Slide ) new Slide()
						.setImageUrl( newImageURLField.getValue() )
						.setTitle( newImageTitle.getValue() )
						.setIsActive( Boolean.TRUE );
				final var createdSlide = service.create( newSlide );
				notifySlideCreated( newSlide.getImageUrl() );
			}
		} );
		newImageButton.setWidthFull();
		newSlideLayout.addAndExpand( newImageTitle, newImageURLField, newImageButton );

		final var existingSlidesData = service.list( Sort.by( "dateModified" ) );
		final var existingSlidesLayout = new VerticalLayout();
		existingSlidesLayout.setPadding( false );
		existingSlidesLayout.setSpacing( false );

		for ( final var existingSlideItem : existingSlidesData ) {
			final var slideItemLayout = new VerticalLayout();
			final var slideItemImg = new Image( existingSlideItem.getImageUrl(), "BBJA Slide Image" );
			out.println( existingSlideItem.getImageUrl() );
			slideItemImg.setWidthFull();
			final var slideItemH = new H2( existingSlideItem.getTitle() );
			final var slideItemP = new Paragraph( "Slide description, slogan, message, detailed content etc. is written here. 🤗" );
			slideItemLayout.add( slideItemImg, slideItemH, slideItemP );

			final var updateSlideLayout = new VerticalLayout();
			updateSlideLayout.setPadding( false );
			updateSlideLayout.setSpacing( false );
			final var editImageUrlField = new TextField( "New Image URL" );
			editImageUrlField.setWidthFull();

			final var updateButton = new Button( "Update Image", onClick -> {
				if ( ! editImageUrlField.getValue().isEmpty() ) {
					final String oldURL = existingSlideItem.getImageUrl();
					final String newURL = editImageUrlField.getValue();
					existingSlideItem.setImageUrl( newURL );
					final Slide updatedSlide = service.update( existingSlideItem );
					notifySlideUpdated( oldURL, newURL );
				}
			} );
			updateButton.setWidthFull();
			updateSlideLayout.addAndExpand( editImageUrlField, updateButton );

			// adding slideLayout and updateSlideLayout to parent layout.
			existingSlidesLayout.addAndExpand( newSlideLayout, slideItemLayout, updateSlideLayout );
		}

		accordion.add( "Add New Slide", newSlideLayout );
		accordion.add( "View/Edit Slide", existingSlidesLayout );
		add( accordion );

		notifyAuthenticatedUser( user );

	}


	private void notifySlideCreated( final String newImageURL ) {

		new Notification( String.format( "Slide image URL is added from %s", newImageURL ) ).open();
	}


	private void notifySlideUpdated( final String oldImageURL, final String newImageURL ) {

		new Notification( String.format( "Slide image URL is updated from %s to %s", oldImageURL, newImageURL ) ).open();
	}


	private void notifyAuthenticatedUser( final AuthenticatedUser user ) {

		final Optional< User > oUser = user.get();
		oUser.ifPresent( u -> {

			new Notification( u.getUsername() + " is logged in.." ).open();
		} );
	}

}
