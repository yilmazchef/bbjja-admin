package be.intecbrussel.bbjja.views.ninjaschool;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.PageService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
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

@PageTitle ( "Offers" )
@Route ( value = "ninja_school/offers", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class OffersView extends VerticalLayout {

	@Autowired
	public OffersView( final AuthenticatedUser user,
	                   final OfferService offerService, final PageService pageService ) {

		final var accordion = new Accordion();
		accordion.setWidthFull();
		accordion.setHeightFull();

		final var newOfferLayout = new VerticalLayout();
		newOfferLayout.setSpacing( false );
		newOfferLayout.setPadding( false );

		final var newOfferTitle = new TextField( "Offer Title" );
		newOfferTitle.setWidthFull();

		int charLimit = 600;
		final var newOfferDescription = new TextArea();
		newOfferDescription.setWidthFull();
		newOfferDescription.setLabel( "Offer Description" );
		newOfferDescription.setMaxLength( charLimit );
		newOfferDescription.setValueChangeMode( ValueChangeMode.EAGER );
		newOfferDescription.addValueChangeListener( onValueChange -> {
			onValueChange.getSource().setHelperText( onValueChange.getValue().length() + "/" + charLimit );
		} );
		newOfferDescription.setValue( "Add description here.." );

		final var newOfferRedirectURL = new TextField( "Offer Redirect URL" );
		newOfferRedirectURL.setWidthFull();

		final var newOfferPageSelect = new Select< Page >();
		newOfferPageSelect.setItems( pageService.list() );
		newOfferPageSelect.setItemLabelGenerator( Page :: getSlug );

		final var newOfferButton = new Button( "Add new offer", onClick -> {
			if ( ! newOfferRedirectURL.getValue().isEmpty() ) {
				final var newOfferRequest = new Offer();
				newOfferRequest.setTitle( newOfferTitle.getValue() );
				newOfferRequest.setDescription( newOfferDescription.getValue() );
				newOfferRequest.setForwardUrl( newOfferRedirectURL.getValue() );
				final Page selectedPage = newOfferPageSelect.getValue();
				newOfferRequest.setPage( selectedPage );
				offerService.create(
						newOfferRequest
				);
			}
		} );
		newOfferButton.setWidthFull();
		newOfferLayout.addAndExpand( newOfferPageSelect, newOfferTitle, newOfferRedirectURL, newOfferDescription, newOfferButton );

		final List< Offer > existingOffersData = offerService.list();

		final var existingOffersLayout = new VerticalLayout();
		existingOffersLayout.setPadding( false );
		existingOffersLayout.setSpacing( false );

		for ( final var existingOfferItem : existingOffersData ) {

			final var existingOfferItemLayout = new VerticalLayout();
			final var existingTitleField = new TextField();
			existingTitleField.setWidthFull();
			existingTitleField.setValue( existingOfferItem.getTitle() );
			final var existingDescriptionField = new TextField();
			existingDescriptionField.setValue( existingOfferItem.getDescription() );
			existingDescriptionField.setWidthFull();
			final var existingForwardUrlField = new TextField();
			existingForwardUrlField.setWidthFull();
			existingForwardUrlField.setValue( existingOfferItem.getForwardUrl() );

			final var existingOfferPageSelect = new Select< Page >();
			existingOfferPageSelect.setItems( pageService.list() );
			existingOfferPageSelect.setItemLabelGenerator( Page :: getSlug );

			final var updateOfferButton = new Button( "Update offer", onSave -> {
				existingOfferItem.setTitle( existingTitleField.getValue() );
				existingOfferItem.setDescription( existingDescriptionField.getValue() );
				existingOfferItem.setForwardUrl( existingForwardUrlField.getValue() );
				existingOfferItem.setPage( existingOfferPageSelect.getValue() );
				offerService.update( existingOfferItem );
			} );
			updateOfferButton.setWidthFull();

			existingOfferItemLayout.add( existingTitleField, existingDescriptionField, existingForwardUrlField, updateOfferButton );
			existingOffersLayout.addAndExpand( existingOfferItemLayout );
		}

		accordion.add( "Add New Offer", newOfferLayout );
		accordion.add( "View/Edit Offers", existingOffersLayout );
		add( accordion );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}

}
