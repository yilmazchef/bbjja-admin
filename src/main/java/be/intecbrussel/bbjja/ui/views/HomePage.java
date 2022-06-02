package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.api.SlideCard;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle ( "BBJJA | Home" )
@Route ( value = "home", layout = MainLayout.class )
@RouteAlias ( value = "", layout = MainLayout.class )
@AnonymousAllowed
@Tag ( "home-page" )
public class HomePage extends VerticalLayout {


	@Autowired
	public HomePage( final SlideService slideService, final SubscriberService subscriberService ) {

		slideService
				.list()
				.stream()
				.map( slide -> new SlideCard( slide, SlideCard.OpenMode.READ ) )
				.forEach( slideCard -> add( slideCard ) );

	}


}
