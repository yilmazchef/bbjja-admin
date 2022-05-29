package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.PageService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle ( "BBJA | Offers" )
@Route ( value = "offers/new", layout = MainLayout.class )
@PermitAll
public class OffersNewView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final OfferService offerService;
	private final PageService pageService;


	public OffersNewView( final AuthenticatedUser authenticatedUser, final OfferService offerService, final PageService pageService ) {

		this.authenticatedUser = authenticatedUser;
		this.offerService = offerService;
		this.pageService = pageService;

		setId( "offers-new" );
		setViewContent( createContent() );

	}


	private Component createContent() {

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


		FlexBoxLayout content = new FlexBoxLayout( newOfferLayout );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
