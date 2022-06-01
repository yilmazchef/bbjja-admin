package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@SpringComponent
@Tag ( "users-view-layout" )
public class UsersViewLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public UsersViewLayout( final AuthenticatedUser authenticatedUser, final UserService userService ) {


		setId( "users-view-layout".concat( String.valueOf( Instant.now().getNano() ) ) );

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
