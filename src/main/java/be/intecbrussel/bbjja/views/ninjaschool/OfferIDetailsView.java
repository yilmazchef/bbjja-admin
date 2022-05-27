package be.intecbrussel.bbjja.views.ninjaschool;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.School;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.SchoolService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import be.intecbrussel.bbjja.views.layouts.VideoLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
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
public class OfferIDetailsView extends VerticalLayout {

	@Autowired
	public OfferIDetailsView( final AuthenticatedUser user,
	                          final SchoolService schoolService, final OfferService offerService ) {

		final var schoolsAccordion = new Accordion();
		schoolsAccordion.setWidthFull();
		schoolsAccordion.setHeightFull();

		final var newSchoolLayout = new VerticalLayout();
		newSchoolLayout.setSpacing( false );
		newSchoolLayout.setPadding( false );

		final var newSchoolTitle = new TextField( "School Name" );
		newSchoolTitle.setWidthFull();

		final var newSchoolCoordinates = new TextField( "Coordinates" );
		newSchoolCoordinates.setPlaceholder( "50.85994129672338, 4.3374293534765815" );
		newSchoolCoordinates.setWidthFull();

		final var newFrameVideo = new VideoLayout( "media/maps.mp4" );
		newFrameVideo.setWidthFull();
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
		newSchoolOfferSelect.setItems( offerService.list() );
		newSchoolOfferSelect.setItemLabelGenerator( Offer :: getTitle );

		final var newFrameButton = new Button( "Add new school", onClick -> {
			if ( ! newSchoolCoordinates.getValue().isEmpty() ) {
				final var newSchoolRequest = new School();
				newSchoolRequest.setTitle( newSchoolTitle.getValue() );
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
		newSchoolLayout.addAndExpand( newSchoolOfferSelect, newSchoolTitle, newSchoolCoordinates, newFrameVideo, newFrameView, newFrameButton );

		final List< School > existingSchoolsData = schoolService.list();

		final var existingSchoolsLayout = new VerticalLayout();
		existingSchoolsLayout.setPadding( false );
		existingSchoolsLayout.setSpacing( false );

		for ( final var existingSchoolItem : existingSchoolsData ) {

			final var existingSchoolItemLayout = new VerticalLayout();
			final var existingTitleField = new TextField();
			existingTitleField.setWidthFull();
			existingTitleField.setValue( existingSchoolItem.getTitle() );
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
			existingFrameView.setValue( "Paste copied iframe code from the map here.." );

			final var existingOfferSelect = new Select< Offer >();
			existingOfferSelect.setItems( offerService.list() );
			existingOfferSelect.setItemLabelGenerator( Offer :: getTitle );

			final var updateSchoolButton = new Button( "Update offer", onSave -> {
				existingSchoolItem.setTitle( existingTitleField.getValue() );
				final var existingCoordinatesArr = existingCoordinatesField.getValue().split( "" );
				existingSchoolItem.setLatitude( Float.parseFloat( existingCoordinatesArr[ 0 ] ) );
				existingSchoolItem.setLongitude( Float.parseFloat( existingCoordinatesArr[ 1 ] ) );
				existingSchoolItem.setOffer( existingOfferSelect.getValue() );
				schoolService.update( existingSchoolItem );
			} );
			updateSchoolButton.setWidthFull();

			existingSchoolItemLayout.add( existingTitleField, existingCoordinatesField, existingFrameView, updateSchoolButton );
			existingSchoolsLayout.addAndExpand( existingSchoolItemLayout );
		}

		schoolsAccordion.add( "Add New Offer", newSchoolLayout );
		schoolsAccordion.add( "View/Edit Offers", existingSchoolsLayout );
		add( schoolsAccordion );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}

}
