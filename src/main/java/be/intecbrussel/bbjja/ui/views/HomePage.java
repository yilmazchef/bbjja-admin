package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.*;
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
	public HomePage( final SlidesCreateLayout slidesCreateLayout, final SlidesViewLayout slidesViewLayout,
	                 final SlidesUpdateLayout slidesUpdateLayout, final SlidesDeleteLayout slidesDeleteLayout,
	                 final SubscribersUpdateLayout subscribersCreateLayout, final SubscribersViewLayout subscribersViewLayout,
	                 final SubscribersUpdateLayout subscribersUpdateLayout, final SubscribersDeleteLayout subscribersDeleteLayout ) {

		final var slides = new VerticalLayout(
				slidesCreateLayout,
				slidesViewLayout,
				slidesDeleteLayout,
				slidesUpdateLayout
		);

		final var subscribers = new VerticalLayout(
				subscribersCreateLayout,
				subscribersViewLayout,
				subscribersDeleteLayout,
				subscribersUpdateLayout
		);

		add(
				slides,
				subscribers
		);

	}

}
