package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle ( "BBJA | Users" )
@Route ( value = "users", layout = MainLayout.class )
@PermitAll
public class UsersQuickView extends ViewFrame implements LocaleChangeObserver {


	private final AuthenticatedUser authenticatedUser;
	private final UserService userService;


	public UsersQuickView( final AuthenticatedUser authenticatedUser, final UserService userService ) {

		this.authenticatedUser = authenticatedUser;
		this.userService = userService;

		setId( "users_quick" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var existingUserData = userService.list();

		final var usersGrid = new Grid<>( User.class, false );
		usersGrid.setAllRowsVisible( true );

		usersGrid.addColumn( User :: getFirstName ).setSortable( true ).setHeader( "First Name" ).setEditorComponent( user -> {
			final var userFirstNameEditorField = new TextField( "set new first name here" );
			userFirstNameEditorField.setWidthFull();
			return userFirstNameEditorField;
		} );
		usersGrid.addColumn( User :: getLastName ).setHeader( "Last Name" );
		usersGrid.addColumn( User :: getEmail ).setHeader( "Email" );
		usersGrid.addColumn(
				new ComponentRenderer<>( Button :: new, ( b, u ) -> {

					b.setIcon( new Icon( VaadinIcon.TRASH ) );
					b.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
					b.addClickListener( onDelete -> {

						if ( u == null ) {
							return;
						}

						userService.delete( u.getId() );
						existingUserData.remove( u );

						if ( existingUserData.size() > 0 ) {
							usersGrid.setVisible( true );
							usersGrid.getDataProvider().refreshAll();
						} else {
							usersGrid.setVisible( false );
						}

						usersGrid.setItems( existingUserData );
						notifyUserDeleted( u );

					} );


				} ) ).setHeader( "Manage" );

		usersGrid.setItems( existingUserData );


		FlexBoxLayout content = new FlexBoxLayout( usersGrid );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifyUserDeleted( final User person ) {

		Notification.show(
				String.format( "User with email %s has been removed from the list.", person.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
