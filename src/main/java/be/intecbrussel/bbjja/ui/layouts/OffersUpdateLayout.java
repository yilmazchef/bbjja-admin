package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Offer;
import be.intecbrussel.bbjja.data.entity.Page;
import be.intecbrussel.bbjja.data.service.OfferService;
import be.intecbrussel.bbjja.data.service.PageService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@Tag ( "offers-update-layout" )
public class OffersUpdateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public OffersUpdateLayout( final AuthenticatedUser authenticatedUser, final OfferService offerService, final PageService pageService ) {


		final List< Offer > existingOffersData = offerService.list();
		final var existingOfferItemLayoutList = new ArrayList< Component >();

		for ( final var existingOfferItem : existingOffersData ) {

			final var existingOfferItemLayout = new VerticalLayout();

			final var existingTitleField = new TextField();
			existingTitleField.setWidthFull();
			existingTitleField.setValue( existingOfferItem.getTitle() );
			final var existingDescriptionField = new TextArea();
			existingDescriptionField.setValue( existingOfferItem.getDescription() );
			existingDescriptionField.setWidthFull();
			existingDescriptionField.setHeight( "200px" );
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

		for ( final Component component : existingOfferItemLayoutList ) {
			add( component );
		}

	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
