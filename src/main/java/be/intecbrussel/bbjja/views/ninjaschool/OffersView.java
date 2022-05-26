package be.intecbrussel.bbjja.views.ninjaschool;


import be.intecbrussel.bbjja.data.dto.NewOfferRequest;
import be.intecbrussel.bbjja.data.dto.OfferResponse;
import be.intecbrussel.bbjja.data.dto.PageResponse;
import be.intecbrussel.bbjja.data.mappers.OfferMapper;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.PageService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
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
	                   final OfferService offerService, final PageService pageService,
	                   final OfferMapper mapper ) {

		final var accordion = new Accordion();
		accordion.setWidthFull();
		accordion.setHeightFull();

		final var newOfferLayout = new VerticalLayout();
		newOfferLayout.setSpacing( false );
		newOfferLayout.setPadding( false );

		final var newOfferTitle = new TextField( "Image Title" );
		newOfferTitle.setWidthFull();
		final var newOfferDescription = new TextField( "Image Title" );
		newOfferDescription.setWidthFull();
		final var newOfferRedirectURL = new TextField( "Image URL" );
		newOfferRedirectURL.setWidthFull();


		// TODO: load pages in a combobox
		final var pagesSelect = new Select< PageResponse >();
		pagesSelect.setItems( pageService.list() );
		pagesSelect.setItemLabelGenerator( PageResponse :: getSlug );

		final var newOfferButton = new Button( "Add new offer", onClick -> {
			if ( ! newOfferRedirectURL.getValue().isEmpty() ) {
				final var newOfferRequest = new NewOfferRequest();
				newOfferRequest.setTitle( newOfferTitle.getValue() );
				newOfferRequest.setDescription( newOfferDescription.getValue() );
				newOfferRequest.setForwardUrl( newOfferRedirectURL.getValue() );
				offerService.create(
						newOfferRequest
				);
			}
		} );
		newOfferButton.setWidthFull();
		newOfferLayout.addAndExpand( newOfferTitle, newOfferRedirectURL, newOfferDescription, newOfferButton );

		final List< OfferResponse > existingOffersData = offerService.list();

		final var existingOffersLayout = new VerticalLayout();
		existingOffersLayout.setPadding( false );
		existingOffersLayout.setSpacing( false );

		for ( final var offerResponse : existingOffersData ) {

			final var existingOfferItemLayout = new VerticalLayout();
			final var titleField = new TextField();
			titleField.setWidthFull();
			titleField.setValue( offerResponse.getTitle() );
			final var descriptionField = new TextField();
			descriptionField.setValue( offerResponse.getDescription() );
			descriptionField.setWidthFull();
			final var forwardUrlField = new TextField();
			forwardUrlField.setWidthFull();
			forwardUrlField.setValue( offerResponse.getForwardUrl() );

			final var updateOfferButton = new Button( "Update offer", onSave -> {
				offerService.update(
						mapper.offerToUpdateOfferRequest(
								mapper.offerResponseToOffer( offerResponse )
						)
				);
			} );
			updateOfferButton.setWidthFull();

			existingOfferItemLayout.add( titleField, descriptionField, updateOfferButton );
			existingOffersLayout.addAndExpand( existingOfferItemLayout );
		}

		accordion.add( "Add New Offer", newOfferLayout );
		accordion.add( "View/Edit Offer", existingOffersLayout );
		add( accordion );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}

}
