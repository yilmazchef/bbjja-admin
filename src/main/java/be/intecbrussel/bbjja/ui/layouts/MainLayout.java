package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.ui.views.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.Getter;

import java.time.Instant;


@SpringComponent
@Tag ( "main-layout" )
// tag::snippet[]
public class MainLayout extends AppLayout {

	@Getter
	private final Tabs subMenuTabs = new Tabs();

	@Getter
	private final H2 subMenuHeader = new H2( "BBJJA Submenu" );


	public MainLayout() {

		setId( "main-layout".concat( String.valueOf( Instant.now().getNano() ) ) );

		final var appTitle = new H1( "BBJJA Admin" );
		appTitle.getStyle()
				.set( "font-size", "var(--lumo-font-size-l)" )
				.set( "line-height", "var(--lumo-size-l)" )
				.set( "margin", "0 var(--lumo-space-m)" );

		final var views = getPrimaryNavigation();

		final var toggle = new DrawerToggle();

		subMenuHeader.getStyle()
				.set( "font-size", "var(--lumo-font-size-l)" )
				.set( "margin", "0" );

		final var wrapper = new HorizontalLayout( toggle, subMenuHeader );
		wrapper.setAlignItems( FlexComponent.Alignment.CENTER );
		wrapper.setSpacing( false );

		final var viewHeader = new VerticalLayout( wrapper, subMenuTabs );
		viewHeader.setPadding( false );
		viewHeader.setSpacing( false );

		addToDrawer( appTitle, views );
		addToNavbar( viewHeader );

		setPrimarySection( Section.DRAWER );
	}
	// end::snippet[]


	private Tabs getPrimaryNavigation() {

		Tabs tabs = new Tabs();
		tabs.add(
				createTab( VaadinIcon.HOME, "Home", HomePage.class ),
				createTab( VaadinIcon.SPECIALIST, "Ninja School", NinjaSchoolPage.class ),
				createTab( VaadinIcon.PACKAGE, "School Grappling", SchoolGrapplingPage.class ),
				createTab( VaadinIcon.PACKAGE, "Street Grappling", StreetGrapplingPage.class ),
				createTab( VaadinIcon.USERS, "Users", UsersPage.class ),
				createTab( VaadinIcon.WORKPLACE, "About", AboutPage.class ),
				createTab( VaadinIcon.RECORDS, "Contact", ContactPage.class )
		);
		tabs.setOrientation( Tabs.Orientation.VERTICAL );
		tabs.setSelectedIndex( 1 );
		return tabs;
	}


	private Tab createTab( VaadinIcon viewIcon, String viewName, Class< ? extends Component > route ) {

		final var icon = viewIcon.create();
		icon.getStyle()
				.set( "box-sizing", "border-box" )
				.set( "margin-inline-end", "var(--lumo-space-m)" )
				.set( "padding", "var(--lumo-space-xs)" );

		final var link = new RouterLink();
		link.setText( viewName );
		link.setRoute( route );
		link.setTabIndex( - 1 );

		return new Tab( link );
	}


	// tag::snippet[]
}
// end::snippet[]
