package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
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
public class SlidesUpdateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SlidesUpdateLayout( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {


		setId( "slides-update-layout" );

		final var existingSlidesData = slideService.list( Sort.by( "dateModified" ) );
		final var existingSlidesLayoutList = new ArrayList< Component >();

		for ( final var existingSlideItem : existingSlidesData ) {

			final var existingSlideItemLayout = new VerticalLayout();
			final var existingSlideItemImage = new Image( existingSlideItem.getImageUrl(), "BBJA Slide Image" );
			out.println( existingSlideItem.getImageUrl() );
			existingSlideItemImage.setWidthFull();

			final var existingSlideItemTitleField = new TextField( "Title" );
			existingSlideItemTitleField.setValue( existingSlideItem.getTitle() );
			existingSlideItemTitleField.setRequiredIndicatorVisible( true );
			existingSlideItemTitleField.setRequired( true );
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

		for ( final Component component : existingSlidesLayoutList ) {
			add( component );
		}

	}


	private void notifySlideUpdated( final String oldImageURL, final String newImageURL ) {

		new Notification( String.format( "Slide image URL is updated from %s to %s", oldImageURL, newImageURL ) ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
