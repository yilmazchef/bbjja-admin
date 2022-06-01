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
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@Tag ( "offers-view-layout" )
public class OffersViewLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public OffersViewLayout( final AuthenticatedUser authenticatedUser, final OfferService offerService, final PageService pageService ) {


		if ( offerService.count() > 0 ) {
			final List< Offer > offers = offerService.list();
			final var components = new ArrayList< VerticalLayout >();

			for ( final var o : offers ) {

				final var layout = new VerticalLayout();

				final var title = new TextField();
				title.setWidthFull();
				title.setValue( o.getTitle() );
				final var description = new TextArea();
				description.setValue( o.getDescription() );
				description.setWidthFull();
				description.setHeight( "200px" );
				final var forwardUrl = new TextField();
				forwardUrl.setWidthFull();
				forwardUrl.setValue( o.getForwardUrl() );

				final var page = new Select< Page >();
				page.setItems( pageService.list() );
				page.setItemLabelGenerator( Page :: getSlug );

				final var update = new Button( "Update offer", onClick -> {
					o.setTitle( title.getValue() );
					o.setDescription( description.getValue() );
					o.setForwardUrl( forwardUrl.getValue() );
					o.setPage( page.getValue() );
					offerService.update( o );
				} );
				update.setWidthFull();

				layout.add( title, description, forwardUrl, update );

				components.add( layout );
			}

			for ( final var component : components ) {
				add( component );
			}
		}


	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
