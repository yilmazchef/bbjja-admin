package be.intecbrussel.bbjja.views.home;


import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@PageTitle ( "Home" )
@Route ( value = "home", layout = MainLayout.class )
@RouteAlias ( value = "", layout = MainLayout.class )
@PermitAll
public class HomeView extends VerticalLayout {

	@Autowired
	public HomeView( final AuthenticatedUser user) {



		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}


}
