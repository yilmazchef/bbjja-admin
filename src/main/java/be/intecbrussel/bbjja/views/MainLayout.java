package be.intecbrussel.bbjja.views;


import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.about.AboutView;
import be.intecbrussel.bbjja.views.about.PartnersView;
import be.intecbrussel.bbjja.views.about.TeamsView;
import be.intecbrussel.bbjja.views.home.HomeView;
import be.intecbrussel.bbjja.views.home.SlidesView;
import be.intecbrussel.bbjja.views.home.SubscribersView;
import be.intecbrussel.bbjja.views.ninjaschool.NinjaSchoolView;
import be.intecbrussel.bbjja.views.ninjaschool.OffersView;
import be.intecbrussel.bbjja.views.schoolgrappling.SchoolGrapplingView;
import be.intecbrussel.bbjja.views.sitesettings.ContactView;
import be.intecbrussel.bbjja.views.sitesettings.SettingsView;
import be.intecbrussel.bbjja.views.sitesettings.UsersView;
import be.intecbrussel.bbjja.views.streetgrappling.StreetGrapplingView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

	/**
	 * A simple navigation item component, based on ListItem element.
	 */
	public static class MenuItemInfo extends ListItem {

		private final Class< ? extends Component > view;


		public MenuItemInfo( String menuTitle, String iconClass, Class< ? extends Component > view ) {

			this.view = view;

			final var link = new RouterLink();
			link.addClassNames( "menu-item-link" );
			link.setRoute( view );

			final var text = new Span( menuTitle );
			text.addClassNames( "menu-item-text" );

			link.add( new LineAwesomeIcon( iconClass ), text );
			add( link );
		}


		public Class< ? > getView() {

			return view;
		}


		/**
		 * Simple wrapper to create icons using LineAwesome iconset. See
		 * https://icons8.com/line-awesome
		 */
		@NpmPackage ( value = "line-awesome", version = "1.3.0" )
		public static class LineAwesomeIcon extends Span {

			public LineAwesomeIcon( String lineawesomeClassnames ) {

				addClassNames( "menu-item-icon" );
				if ( ! lineawesomeClassnames.isEmpty() ) {
					addClassNames( lineawesomeClassnames );
				}
			}

		}

	}

	private H1 viewTitle;

	private final AuthenticatedUser user;
	private final AccessAnnotationChecker checker;


	public MainLayout( final AuthenticatedUser user, final AccessAnnotationChecker checker ) {

		this.user = user;
		this.checker = checker;

		setPrimarySection( Section.DRAWER );
		addToNavbar( true, createHeaderContent() );
		addToDrawer( createDrawerContent() );
	}


	private Component createHeaderContent() {

		final var toggle = new DrawerToggle();
		toggle.addClassNames( "view-toggle" );
		toggle.addThemeVariants( ButtonVariant.LUMO_CONTRAST );
		toggle.getElement().setAttribute( "aria-label", "Menu toggle" );

		viewTitle = new H1();
		viewTitle.addClassNames( "view-title" );

		final var header = new Header( toggle, viewTitle );
		header.addClassNames( "view-header" );
		return header;
	}


	private Component createDrawerContent() {

		final var appName = new H2( "BBJJA Admin" );
		appName.addClassNames( "app-name" );

		final var section = new com.vaadin.flow.component.html.Section( appName,
				createNavigation(), createFooter() );
		section.addClassNames( "drawer-section" );
		return section;
	}


	private Nav createNavigation() {

		final var nav = new Nav();
		nav.addClassNames( "menu-item-container" );
		nav.getElement().setAttribute( "aria-labelledby", "views" );

		// Wrap the links in a list; improves accessibility
		final var list = new UnorderedList();
		list.addClassNames( "navigation-list" );
		nav.add( list );

		for ( final var menuItem : createMenuItems() ) {
			if ( checker.hasAccess( menuItem.getView() ) ) {
				list.add( menuItem );
			}

		}
		return nav;
	}


	private MenuItemInfo[] createMenuItems() {

		final var home = new MenuItemInfo( "Home", "la la-home", HomeView.class );
		final var slides = new MenuItemInfo( "Slides", "lab la-slideshare", SlidesView.class );
		final var schoolGrappling = new MenuItemInfo( "School Grappling", "la la-file", SchoolGrapplingView.class );
		final var streetGrappling = new MenuItemInfo( "Street Grappling", "la la-street-view", StreetGrapplingView.class );
		final var ninjaSchool = new MenuItemInfo( "Ninja School", "la la-user-ninja", NinjaSchoolView.class );
		final var offers = new MenuItemInfo( "Offers", "lab la-wpforms", OffersView.class );
		final var users = new MenuItemInfo( "Users", "la la-users", UsersView.class );
		final var subscribers = new MenuItemInfo( "Subscribers", "la la-mail-bulk", SubscribersView.class );
		final var about = new MenuItemInfo( "About", "la la-info-circle", AboutView.class );
		final var teams = new MenuItemInfo( "Teams", "lab la-teamspeak", TeamsView.class );
		final var partners = new MenuItemInfo( "Partners", "la la-user-friends", PartnersView.class );
		final var contact = new MenuItemInfo( "Contact", "la la-phone", ContactView.class );
		final var settings = new MenuItemInfo( "Settings", "lab la-readme", SettingsView.class );

		return new MenuItemInfo[]{ //
				home, //
				// slides, //
				// subscribers, //
				schoolGrappling, //
				streetGrappling, //
				ninjaSchool, //
				offers, //
				// users, //
				about, //
				// teams, //
				// partners, //
				// contact, //
				settings, //

		};
	}


	private Footer createFooter() {

		final var layout = new Footer();
		layout.addClassNames( "footer" );

		final var oUser = user.get();
		if ( oUser.isPresent() ) {
			final var u = oUser.get();

			final var avatar = new Avatar( u.getFirstName(), u.getProfilePictureUrl() );
			avatar.addClassNames( "me-xs" );

			final var userMenu = new ContextMenu( avatar );
			userMenu.setOpenOnClick( true );
			userMenu.addItem( "Logout", e -> {
				user.logout();
			} );

			final var name = new Span( u.getFirstName() );
			name.addClassNames( "font-medium", "text-s", "text-secondary" );

			layout.add( avatar, name );
		} else {
			Anchor loginLink = new Anchor( "login", "Sign in" );
			layout.add( loginLink );
		}

		return layout;
	}


	@Override
	protected void afterNavigation() {

		super.afterNavigation();
		viewTitle.setText( getCurrentPageTitle() );
	}


	private String getCurrentPageTitle() {

		final var title = getContent().getClass().getAnnotation( PageTitle.class );
		return title == null ? "" : title.value();
	}

}
