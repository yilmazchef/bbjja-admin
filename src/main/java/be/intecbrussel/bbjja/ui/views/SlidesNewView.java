package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle ( "BBJA | Slides" )
@Route ( value = "slides/new", layout = MainLayout.class )
@PermitAll
public class SlidesNewView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final SlideService slideService;


	public SlidesNewView( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {

		this.authenticatedUser = authenticatedUser;
		this.slideService = slideService;

		setId( "slides-new" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var newSlideLayout = new VerticalLayout();
		newSlideLayout.setSpacing( false );
		newSlideLayout.setPadding( false );

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

		FlexBoxLayout content = new FlexBoxLayout( newSlideLayout );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifySlideCreated( final String newImageURL ) {

		new Notification( String.format( "Slide image URL is added from %s", newImageURL ) ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
