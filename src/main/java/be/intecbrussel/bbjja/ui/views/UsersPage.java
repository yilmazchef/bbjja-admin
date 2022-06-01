package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;


@PageTitle ( "BBJJA | Users" )
@Route ( value = "users", layout = MainLayout.class )
@RolesAllowed ( { "ADMIN" } )
@Tag ( "users-page" )
public class UsersPage extends VerticalLayout {

	public UsersPage() {

		setId( "users-page".concat( String.valueOf( Instant.now().getNano() ) ) );
	}

}
