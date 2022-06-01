package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.PageService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@SpringComponent
@Tag ( "offers-create-layout" )
public class OffersCreateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public OffersCreateLayout( final AuthenticatedUser authenticatedUser, final OfferService offerService, final PageService pageService ) {

		setId( "offers-create-layout".concat( String.valueOf( Instant.now().getNano() ) ) );

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

		add( newOfferLayout );
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
