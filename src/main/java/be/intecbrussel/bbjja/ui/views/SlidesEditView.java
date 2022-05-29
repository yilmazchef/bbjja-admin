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
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Sort;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;

import static java.lang.System.out;

@PageTitle ( "BBJA | Slides" )
@Route ( value = "slides/edit", layout = MainLayout.class )
@PermitAll
public class SlidesEditView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final SlideService slideService;


	public SlidesEditView( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {

		this.authenticatedUser = authenticatedUser;
		this.slideService = slideService;

		setId( "slides-edit" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var existingSlidesData = slideService.list( Sort.by( "dateModified" ) );
		final var existingSlidesLayoutList = new ArrayList< Component >();

		for ( final var existingSlideItem : existingSlidesData ) {

			final var existingSlideItemLayout = new VerticalLayout();
			final var existingSlideItemImage = new Image( existingSlideItem.getImageUrl(), "BBJA Slide Image" );
			out.println( existingSlideItem.getImageUrl() );
			existingSlideItemImage.setWidthFull();

			final var existingSlideItemTitleField = new TextField( "Title" );
			existingSlideItemTitleField.setValue( existingSlideItem.getPage().getSlug() );
			existingSlideItemTitleField.setWidthFull();

			final var existingDescriptionField = new TextField( "Page slug" );
			existingDescriptionField.setValue( existingSlideItem.getPage().getSlug() );
			existingDescriptionField.setWidthFull();

			existingSlideItemLayout.add( existingSlideItemImage, existingSlideItemTitleField, existingDescriptionField );

			final var updateSlideLayout = new VerticalLayout();
			updateSlideLayout.setPadding( false );
			updateSlideLayout.setSpacing( false );
			final var updateSlideTitleField = new TextField( "Title" );
			updateSlideTitleField.setWidthFull();
			final var updateSlideImageUrlField = new TextField( "Image URL" );
			updateSlideImageUrlField.setWidthFull();

			final var updateButton = new Button( "Update Slide", onClick -> {
				if ( ! updateSlideImageUrlField.getValue().isEmpty() ) {
					final String oldURL = existingSlideItem.getImageUrl();
					final String newURL = updateSlideImageUrlField.getValue();
					final String newTitle = updateSlideTitleField.getValue();
					existingSlideItem.setImageUrl( newURL );
					existingSlideItem.setTitle( newTitle );
					final Slide updatedSlide = slideService.update( existingSlideItem );
					notifySlideUpdated( oldURL, newURL );
				}
			} );
			updateButton.setWidthFull();
			updateSlideLayout.addAndExpand( updateSlideImageUrlField, updateButton );

			existingSlideItemLayout.add( updateSlideLayout );

			existingSlidesLayoutList.add( existingSlideItemLayout );

		}


		final var content = new FlexBoxLayout( existingSlidesLayoutList.toArray( Component[] :: new ) );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifySlideUpdated( final String oldImageURL, final String newImageURL ) {

		new Notification( String.format( "Slide image URL is updated from %s to %s", oldImageURL, newImageURL ) ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
