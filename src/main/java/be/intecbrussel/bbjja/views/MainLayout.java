package be.intecbrussel.bbjja.views;


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
				createTab( "Home", HomeView.class ),
				createTab( "Ninja School", NinjaSchoolView.class ),
				createTab( "School Grappling", SchoolGrapplingView.class ),
				createTab( "Street Grappling", StreetGrapplingView.class ),
				createTab( "Site Settings", SettingsView.class ),
				createTab( "About", AboutView.class ),
				createTab( "My Profile", ProfileView.class )
		);
		tabs.addThemeVariants( TabsVariant.LUMO_CENTERED, TabsVariant.LUMO_SMALL );
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