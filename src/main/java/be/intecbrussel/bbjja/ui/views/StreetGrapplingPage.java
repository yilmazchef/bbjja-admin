package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Grappling;
import be.intecbrussel.bbjja.data.entity.Grappling.GrapplingType;
import be.intecbrussel.bbjja.data.service.GrapplingService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import be.intecbrussel.bbjja.ui.layouts.YouTubeVideoLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;

@PageTitle ( "Grappling" )
@Route ( value = "grappling/street", layout = MainLayout.class )
@RolesAllowed ( { "EDITOR", "ADMIN" } )
@Tag ( "street-grappling-page" )
public class StreetGrapplingPage extends VerticalLayout {

	@Autowired
	public StreetGrapplingPage( final AuthenticatedUser authenticatedUser, final GrapplingService grapplingService ) {

		setId( "street-grappling-page".concat( String.valueOf( Instant.now().getNano() ) ) );

		final var accordion = new Accordion();
		accordion.setSizeFull();

		final var newGrapplingLayout = new VerticalLayout();
		newGrapplingLayout.setSpacing( false );
		newGrapplingLayout.setPadding( false );

		final var newGrapplingTitleField = new TextField( "Title" );
		newGrapplingTitleField.setWidthFull();

		final var newGrapplingForWhoField = new TextField( "For who?" );
		newGrapplingForWhoField.setWidthFull();

		int charLimit = 2000;
		final var newGrapplingPracticeArea = new TextArea();
		newGrapplingPracticeArea.setWidthFull();
		newGrapplingPracticeArea.setLabel( "Practice" );
		newGrapplingPracticeArea.setMaxLength( charLimit );
		newGrapplingPracticeArea.setValueChangeMode( ValueChangeMode.EAGER );
		newGrapplingPracticeArea.addValueChangeListener( onValueChange -> {
			onValueChange.getSource().setHelperText( onValueChange.getValue().length() + "/" + charLimit );
		} );
		newGrapplingPracticeArea.setValue( "Add description/practices here.." );


		final var newGrapplingVideoField = new TextField( "Video URL" );
		newGrapplingVideoField.setWidthFull();
		final var newGrapplingButton = new Button( "Submit new street grappling request", onClick -> {
			if ( ! newGrapplingVideoField.getValue().isEmpty() ) {
				final var newGrappling = ( Grappling ) new Grappling()
						.setVideoUrl( newGrapplingVideoField.getValue() )
						.setIntroduction( newGrapplingTitleField.getValue() )
						.setForWho( newGrapplingForWhoField.getValue() )
						.setPractice( newGrapplingPracticeArea.getValue() )
						.setType( GrapplingType.STREET )
						.setIsActive( Boolean.TRUE );
				final var createdGrappling = grapplingService.create( newGrappling );
				notifyGrapplingCreated( newGrappling.getVideoUrl() );
			}
		} );
		newGrapplingButton.setWidthFull();
		newGrapplingLayout.addAndExpand(
				newGrapplingTitleField, newGrapplingForWhoField,
				newGrapplingVideoField, newGrapplingPracticeArea,
				newGrapplingButton
		);
		accordion.add( "Add new street grappling", newGrapplingLayout );

		final var existingGrapplingData = grapplingService.street();
		for ( final var existingGrapplingItem : existingGrapplingData ) {

			final var existingGrapplingItemLayout = new VerticalLayout();

			// TODO: experimental
			final var existingGrapplingItemYTVideo = new YouTubeVideoLayout( existingGrapplingItem.getVideoUrl() );
			existingGrapplingItemYTVideo.setSize( "100%", "320px" );

			final var existingGrapplingItemIntroduction = new H2( existingGrapplingItem.getIntroduction() );
			final var existingGrapplingItemPractice = new Paragraph( existingGrapplingItem.getPractice() );
			existingGrapplingItemLayout.add(
					existingGrapplingItemIntroduction, existingGrapplingItemPractice,
					existingGrapplingItemYTVideo
			);

			final var updateGrapplingLayout = new VerticalLayout();
			updateGrapplingLayout.setPadding( false );
			updateGrapplingLayout.setSpacing( false );
			final var updateGrapplingIntroField = new TextField( "Title" );
			updateGrapplingIntroField.setWidthFull();
			updateGrapplingIntroField.setValue( existingGrapplingItem.getIntroduction() );
			final var updateGrapplingForWhoField = new TextField( "For Who?" );
			updateGrapplingForWhoField.setWidthFull();
			updateGrapplingForWhoField.setValue( existingGrapplingItem.getForWho() );

			final var updateGrapplingPracticeArea = new TextArea();
			updateGrapplingPracticeArea.setWidthFull();
			updateGrapplingPracticeArea.setLabel( "Practice" );
			updateGrapplingPracticeArea.setMaxLength( charLimit );
			updateGrapplingPracticeArea.setValueChangeMode( ValueChangeMode.EAGER );
			updateGrapplingPracticeArea.addValueChangeListener( onValueChange -> {
				onValueChange.getSource().setHelperText( onValueChange.getValue().length() + "/" + charLimit );
			} );
			updateGrapplingPracticeArea.setValue( existingGrapplingItem.getPractice() );

			final var updateGrapplingVideoField = new TextField( "Video URL" );
			updateGrapplingVideoField.setWidthFull();
			updateGrapplingVideoField.setValue( existingGrapplingItem.getVideoUrl() );

			final var updateButton = new Button( "Update street grappling", onClick -> {
				if ( ! updateGrapplingVideoField.getValue().isEmpty() ) {
					final var oldURL = existingGrapplingItem.getVideoUrl();
					final var newURL = updateGrapplingVideoField.getValue();
					final var newTitle = updateGrapplingIntroField.getValue();
					final var newForWho = updateGrapplingForWhoField.getValue();
					existingGrapplingItem.setIntroduction( newTitle );
					existingGrapplingItem.setVideoUrl( newURL );
					existingGrapplingItem.setForWho( newForWho );
					existingGrapplingItem.setType( GrapplingType.STREET );
					final var updatedGrappling = grapplingService.update( existingGrapplingItem );
					notifyGrapplingUpdated( oldURL, newURL );
				}
			} );
			updateButton.setWidthFull();
			updateGrapplingLayout.addAndExpand(
					updateGrapplingIntroField, updateGrapplingForWhoField,
					updateGrapplingVideoField, updateGrapplingPracticeArea,
					updateButton
			);

			existingGrapplingItemLayout.add( updateGrapplingLayout );

			accordion.add( String.format( "View/Edit %s ", existingGrapplingItem.getIntroduction() ), existingGrapplingItemLayout );
		}

		accordion.open( 0 );
		add( accordion );

	}


	private void notifyGrapplingCreated( final String newVideoURL ) {

		new Notification( String.format( "Grappling image URL is added from %s", newVideoURL ) ).open();
	}


	private void notifyGrapplingUpdated( final String oldVideoURL, final String newVideoURL ) {

		new Notification( String.format( "Grappling image URL is updated from %s to %s", oldVideoURL, newVideoURL ) ).open();
	}

}
