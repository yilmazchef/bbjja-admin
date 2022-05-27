package be.intecbrussel.bbjja.views;


import be.intecbrussel.bbjja.views.home.SlidesView;
import be.intecbrussel.bbjja.views.home.SubscribersView;
import be.intecbrussel.bbjja.views.sitesettings.SettingsView;
import be.intecbrussel.bbjja.views.sitesettings.UsersView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

	public MainLayout() {

		H1 title = new H1( "BBJJA Admin" );
		title.getStyle()
				.set( "font-size", "var(--lumo-font-size-l)" )
				.set( "margin", "var(--lumo-space-m) var(--lumo-space-l)" );

		Tabs tabs = getTabs();

		H2 viewTitle = new H2( "View title" );
		Paragraph viewContent = new Paragraph( "View content" );

		Div content = new Div();
		content.add( viewTitle, viewContent );

		addToNavbar( title );
		addToNavbar( true, tabs );

		setContent( content );
	}


	private Tabs getTabs() {

		Tabs tabs = new Tabs();
		tabs.add(
				createTab( VaadinIcon.SLIDERS, "Slides", SlidesView.class ),
				createTab( VaadinIcon.COGS, "Settings", SettingsView.class ),
				createTab( VaadinIcon.USERS, "Users", UsersView.class ),
				createTab( VaadinIcon.SUBSCRIPT, "Subscribers", SubscribersView.class )
		);
		tabs.addThemeVariants( TabsVariant.LUMO_MINIMAL, TabsVariant.LUMO_EQUAL_WIDTH_TABS );
		return tabs;
	}


	private Tab createTab( VaadinIcon viewIcon, String viewName, Class< ? extends Component > viewRoute ) {

		final var icon = viewIcon.create();
		icon.setSize( "var(--lumo-icon-size-s)" );
		icon.getStyle().set( "margin", "auto" );

		final var link = new RouterLink();
		link.setText( viewName );
		link.add( icon );
		link.setRoute( viewRoute );
		link.setTabIndex( - 1 );

		return new Tab( link );
	}

}