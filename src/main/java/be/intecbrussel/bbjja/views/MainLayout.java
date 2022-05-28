package be.intecbrussel.bbjja.views;


import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

	public MainLayout( AuthenticatedUser authenticatedUser ) {

		final var tabs = new Tabs();
		tabs.add(
				createTab( "Home", HomeView.class ),
				createTab( "Ninja School", NinjaSchoolView.class ),
				createTab( "School Grappling", SchoolGrapplingView.class ),
				createTab( "Street Grappling", StreetGrapplingView.class ),
				createTab( "Site Settings", SettingsView.class ),
				createTab( "About", AboutView.class ),
				createTab( "Profile", ProfileView.class )
		);
		tabs.addThemeVariants( TabsVariant.LUMO_CENTERED, TabsVariant.LUMO_SMALL );
		addToNavbar( true, tabs );

		final var toggleButton = new Button( "Dark Theme", click -> {
			ThemeList themeList = this.getElement().getThemeList(); // (1)

			if ( themeList.contains( Lumo.DARK ) ) {
				themeList.remove( Lumo.DARK );
				click.getSource().setText( "Light Theme" );
			} else {
				themeList.add( Lumo.DARK );
				click.getSource().setText( "Dark Theme" );
			}
		} );

		final var logoutButton = new Button( "Log out", e -> authenticatedUser.logout() );

		if ( authenticatedUser.get().isPresent() ) {
			addToDrawer( toggleButton, logoutButton );
		} else {
			addToDrawer( toggleButton );
		}
	}


	private Tab createTab( final String viewName, final Class< ? extends Component > viewRoute ) {

		final var link = new RouterLink();
		link.setText( viewName );
		link.setRoute( viewRoute );
		link.setTabIndex( - 1 );

		return new Tab( link );
	}

}