package be.intecbrussel.bbjja.views.ninjaschool;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.School;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.SchoolService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import be.intecbrussel.bbjja.views.layouts.Video;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@PageTitle ( "Offer Details" )
@Route ( value = "ninja_school/offer_details", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class OfferDetailsView extends VerticalLayout {

	@Autowired
	public OfferDetailsView( final AuthenticatedUser user,
	                         final SchoolService schoolService, final OfferService offerService ) {

		final var schoolsAccordion = new Accordion();
		schoolsAccordion.setWidthFull();
		schoolsAccordion.setHeightFull();

		final var newSchoolLayout = new VerticalLayout();
		newSchoolLayout.setSpacing( false );
		newSchoolLayout.setPadding( false );

		final var newSchoolTitle = new TextField( "School Name" );
		newSchoolTitle.setWidthFull();

		final var newSchoolPhone = new TextField( "School Contact Phone" );
		newSchoolPhone.setWidthFull();

		final var newSchoolCoordinates = new TextField( "Coordinates" );
		newSchoolCoordinates.setPlaceholder( "50.85994129672338, 4.3374293534765815" );
		newSchoolCoordinates.setWidthFull();

		final var newFrameVideo = new Video( "./videos/maps.mp4" );
		add( newFrameVideo );

		int charLimit = 2048;
		final var newFrameView = new TextArea();
		newFrameView.setWidthFull();
		newFrameView.setLabel( "iFrame Maps" );
		newFrameView.setMaxLength( charLimit );
		newFrameView.setValueChangeMode( ValueChangeMode.EAGER );
		newFrameView.addValueChangeListener( onValueChange -> {
			onValueChange.getSource().setHelperText( onValueChange.getValue().length() + "/" + charLimit );
		} );
		newFrameView.setValue( "Paste copied iframe code from the map here.." );

		final var newSchoolOfferSelect = new Select< Offer >();
		final List< Offer > offersData = offerService.list();
		newSchoolOfferSelect.setItems( offersData );
		newSchoolOfferSelect.setItemLabelGenerator( Offer :: getTitle );
		newSchoolOfferSelect.setEmptySelectionAllowed( false );
		newSchoolOfferSelect.setValue( offersData.get( 0 ) );

		final var newFrameButton = new Button( "Add new school", onClick -> {
			if ( ! newSchoolCoordinates.getValue().isEmpty() ) {
				final var newSchoolRequest = new School();
				newSchoolRequest.setTitle( newSchoolTitle.getValue() );
				newSchoolRequest.setPhone( newSchoolPhone.getValue() );
				final var coordinatesArr = newSchoolCoordinates.getValue().split( "," );
				newSchoolRequest.setLatitude( Float.parseFloat( coordinatesArr[ 0 ] ) );
				newSchoolRequest.setLongitude( Float.parseFloat( coordinatesArr[ 1 ] ) );
				final Offer selectedOffer = newSchoolOfferSelect.getValue();
				newSchoolRequest.setOffer( selectedOffer );
				schoolService.create(
						newSchoolRequest
				);
			}
		} );
		newFrameButton.setWidthFull();
		newSchoolLayout.addAndExpand( newSchoolOfferSelect, newSchoolTitle, newSchoolPhone,
				newSchoolCoordinates, newFrameVideo, newFrameView, newFrameButton );

		schoolsAccordion.add( "Add New Offer", newSchoolLayout );

		final List< School > existingSchoolsData = schoolService.list();

		for ( final var existingSchoolItem : existingSchoolsData ) {

			final var existingSchoolItemLayout = new VerticalLayout();

			final var existingTitleField = new TextField();
			existingTitleField.setWidthFull();
			existingTitleField.setValue( existingSchoolItem.getTitle() );

			final var existingPhoneField = new TextField();
			existingPhoneField.setWidthFull();
			existingPhoneField.setValue( existingSchoolItem.getPhone() );

			final var existingCoordinatesField = new TextField();
			existingCoordinatesField.setValue( existingSchoolItem.getLatitude() + "," + existingSchoolItem.getLongitude() );
			existingCoordinatesField.setWidthFull();

			final var existingFrameView = new TextArea();
			existingFrameView.setWidthFull();
			existingFrameView.setLabel( "iFrame Maps" );
			existingFrameView.setMaxLength( charLimit );
			existingFrameView.setValueChangeMode( ValueChangeMode.EAGER );
			existingFrameView.addValueChangeListener( onValueChange -> {
				onValueChange.getSource().setHelperText( onValueChange.getValue().length() + "/" + charLimit );
			} );
			existingFrameView.setValue( existingSchoolItem.getIframe() );

			final var existingOfferSelect = new Select< Offer >();
			existingOfferSelect.setItems( offersData );
			existingOfferSelect.setItemLabelGenerator( Offer :: getTitle );
			existingOfferSelect.setEmptySelectionAllowed( false );
			existingOfferSelect.setValue( existingSchoolItem.getOffer() );

			final var updateSchoolButton = new Button( "Update offer", onSave -> {
				existingSchoolItem.setTitle( existingTitleField.getValue() );
				existingSchoolItem.setPhone( existingPhoneField.getValue() );
				final var existingCoordinatesArr = existingCoordinatesField.getValue().split( "" );
				existingSchoolItem.setLatitude( Float.parseFloat( existingCoordinatesArr[ 0 ] ) );
				existingSchoolItem.setLongitude( Float.parseFloat( existingCoordinatesArr[ 1 ] ) );
				existingSchoolItem.setOffer( existingOfferSelect.getValue() );
				final School updatedSchoolItem = schoolService.update( existingSchoolItem );
				notifySchoolUpdatedSuccess( updatedSchoolItem );
			} );
			updateSchoolButton.setWidthFull();

			existingSchoolItemLayout.add( existingOfferSelect, existingTitleField, existingPhoneField,
					existingCoordinatesField, existingFrameView, updateSchoolButton );

			schoolsAccordion.add( existingSchoolItem.getTitle(), existingSchoolItemLayout );
		}

		add( schoolsAccordion );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}


	private void notifySchoolUpdatedSuccess( final School school ) {

		Notification.show( String.format( "School %s is updated.", school.getId() ), 3000, Notification.Position.BOTTOM_CENTER ).open();

	}

}
