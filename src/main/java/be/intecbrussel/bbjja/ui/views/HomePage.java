package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.*;
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
public class HomePage extends VerticalLayout {

	@Autowired
	public HomePage( final SlidesCreateLayout slidesCreateLayout, final SlidesViewLayout slidesViewLayout,
	                 final SlidesUpdateLayout slidesUpdateLayout, final SlidesDeleteLayout slidesDeleteLayout,
	                 final SubscribersUpdateLayout subscribersCreateLayout, final SubscribersViewLayout subscribersViewLayout,
	                 final SubscribersUpdateLayout subscribersUpdateLayout, final SubscribersDeleteLayout subscribersDeleteLayout ) {

		setId( "home" );

		final var slidesLayout = new VerticalLayout(
				slidesCreateLayout,
				slidesViewLayout,
				slidesDeleteLayout,
				slidesUpdateLayout
		);

		final var subscribersLayout = new VerticalLayout(
				subscribersCreateLayout,
				subscribersViewLayout,
				subscribersDeleteLayout,
				subscribersUpdateLayout
		);


		add(
				slidesLayout,
				subscribersLayout
		);
	}

}
