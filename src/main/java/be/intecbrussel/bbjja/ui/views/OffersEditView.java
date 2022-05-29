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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@PageTitle ( "BBJA | Offers" )
@Route ( value = "offers/edit", layout = MainLayout.class )
@PermitAll
public class OffersEditView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final OfferService offerService;
	private final PageService pageService;


	public OffersEditView( final AuthenticatedUser authenticatedUser, final OfferService offerService, final PageService pageService ) {

		this.authenticatedUser = authenticatedUser;
		this.offerService = offerService;
		this.pageService = pageService;

		setId( "offers-edit" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final List< Offer > existingOffersData = offerService.list();
		final var existingOfferItemLayoutList = new ArrayList< Component >();

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

			existingOfferItemLayoutList.add( existingOfferItemLayout );
		}

		FlexBoxLayout content = new FlexBoxLayout( existingOfferItemLayoutList.toArray( Component[] :: new ) );
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
