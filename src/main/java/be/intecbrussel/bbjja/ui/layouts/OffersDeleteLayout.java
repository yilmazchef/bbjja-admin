package be.intecbrussel.bbjja.ui.layouts;


import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
@Tag ( "offers-delete-layout" )
public class OffersDeleteLayout extends VerticalLayout implements LocaleChangeObserver {

	public OffersDeleteLayout() {


	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
