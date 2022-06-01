package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;


@PageTitle ( "BBJJA | Contact" )
@Route ( value = "contact", layout = MainLayout.class )
@RolesAllowed ( { "EDITOR", "ADMIN" } )
@Tag ( "contact-page" )
public class ContactPage extends VerticalLayout {

	public ContactPage() {

		setId( "contact".concat( String.valueOf( Instant.now().getNano() ) ) );
	}

}
