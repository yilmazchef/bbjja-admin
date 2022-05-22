package be.intecbrussel.bbjja.views.ninjaschool;


import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@PageTitle ( "Offers" )
@Route ( value = "ninja_school/offers", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class OffersView extends VerticalLayout {

	@Autowired
	public OffersView( final AuthenticatedUser user ) {


		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}

}
