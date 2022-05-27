package be.intecbrussel.bbjja.views;


import be.intecbrussel.bbjja.views.grappling.SchoolGrapplingView;
import be.intecbrussel.bbjja.views.home.SlidesView;
import be.intecbrussel.bbjja.views.home.SubscribersView;
import be.intecbrussel.bbjja.views.ninjaschool.OffersView;
import be.intecbrussel.bbjja.views.sitesettings.UsersView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

	public MainLayout() {

		final var tabs = new Tabs();
		tabs.add(
				createTab( "Home -> Slides", SlidesView.class ),
				createTab( "Ninja School -> Offers", OffersView.class ),
				createTab( "Grappling -> School", SchoolGrapplingView.class ),
				createTab( "Grappling -> Street", SchoolGrapplingView.class ),
				createTab( "Admin -> Users", UsersView.class ),
				createTab( "Admin -> Subscribers", SubscribersView.class )
		);
		tabs.addThemeVariants( TabsVariant.LUMO_CENTERED, TabsVariant.LUMO_EQUAL_WIDTH_TABS );
		addToNavbar( true, tabs );
	}


	private Tab createTab( final String viewName, final Class< ? extends Component > viewRoute ) {

		final var link = new RouterLink();
		link.setText( viewName );
		link.setRoute( viewRoute );
		link.setTabIndex( - 1 );

		return new Tab( link );
	}

}