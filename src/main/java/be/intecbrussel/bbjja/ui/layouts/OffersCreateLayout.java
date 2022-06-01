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

@SpringComponent
@Tag ( "offers-create-layout" )
public class OffersCreateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public OffersCreateLayout( final AuthenticatedUser authenticatedUser, final OfferService offerService, final PageService pageService ) {

		final var layout = new VerticalLayout();

		final var title = new TextField( "Title" );
		title.setWidthFull();

		int charLimit = 600;
		final var description = new TextArea();
		description.setWidthFull();
		description.setLabel( "Description" );
		description.setMaxLength( charLimit );
		description.setValueChangeMode( ValueChangeMode.EAGER );
		description.addValueChangeListener( onValueChange -> {
			onValueChange.getSource().setHelperText( onValueChange.getValue().length() + "/" + charLimit );
		} );
		description.setValue( "Description (Max. 600 characters)" );

		final var redirectUrl = new TextField( "Redirect URL" );
		redirectUrl.setWidthFull();

		final var page = new Select< Page >();
		page.setItems( pageService.list() );
		page.setItemLabelGenerator( Page :: getSlug );

		final var create = new Button( "Add new offer", onClick -> {
			if ( ! redirectUrl.getValue().isEmpty() ) {
				final var request = new Offer();
				request.setTitle( title.getValue() );
				request.setDescription( description.getValue() );
				request.setForwardUrl( redirectUrl.getValue() );
				final Page selectedPage = page.getValue();
				request.setPage( selectedPage );

				offerService.create( request );
			}
		} );
		create.setWidthFull();
		layout.addAndExpand( page, title, redirectUrl, description, create );

		add( layout );
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
