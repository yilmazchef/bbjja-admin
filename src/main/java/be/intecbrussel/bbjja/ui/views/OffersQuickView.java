package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle ( "BBJA | Offers" )
@Route ( value = "offers", layout = MainLayout.class )
@PermitAll
public class OffersQuickView extends ViewFrame implements LocaleChangeObserver {

	public OffersQuickView() {

		setId( "offers-quick" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var comp = new Label( "CONTENT" );

		FlexBoxLayout content = new FlexBoxLayout( comp );
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
