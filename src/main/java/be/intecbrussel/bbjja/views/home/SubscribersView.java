package be.intecbrussel.bbjja.views.home;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@PageTitle ( "Subscribers" )
@Route ( value = "subscribers", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class SubscribersView extends VerticalLayout {

	private final AuthenticatedUser user;
	private final SubscriberService service;

	private Grid< Subscriber > grid;
	private Div hint;

	private final List< Subscriber > subscribersData = new ArrayList<>();


	public SubscribersView( final AuthenticatedUser user, final SubscriberService service ) {

		this.user = user;
		this.service = service;

		this.subscribersData.addAll( this.service.list() );

		setupInvitationForm();
		setupGrid();
	}


	private void setupInvitationForm() {

		final var comboBox = new ComboBox< Subscriber >();
		comboBox.setItems( this.subscribersData );
		comboBox.setItemLabelGenerator( Subscriber :: getEmail );

		final var button = new Button( "Send invite" );
		button.addThemeVariants( ButtonVariant.LUMO_PRIMARY );
		button.addClickListener( e -> {
			sendInvitation( comboBox.getValue() );
			comboBox.setValue( null );
		} );

		final var layout = new HorizontalLayout( comboBox, button );
		layout.setFlexGrow( 1, comboBox );

		add( layout );
	}


	private void setupGrid() {

		grid = new Grid<>( Subscriber.class, false );
		grid.setAllRowsVisible( true );
		grid.addColumn( Subscriber :: getFirstName ).setHeader( "First Name" );
		grid.addColumn( Subscriber :: getLastName ).setHeader( "Last Name" );
		grid.addColumn( Subscriber :: getEmail ).setHeader( "Email" );

		grid.addColumn(
				new ComponentRenderer<>( Button :: new, ( button, person ) -> {
					button.addThemeVariants( ButtonVariant.LUMO_ICON,
							ButtonVariant.LUMO_ERROR,
							ButtonVariant.LUMO_TERTIARY );
					button.addClickListener( e -> this.removeInvitation( person ) );
					button.setIcon( new Icon( VaadinIcon.TRASH ) );
					button.addClickListener( onClick -> {
						this.subscribersData.remove( person );
						this.service.delete( person.getId() );
						this.grid.setItems( this.subscribersData );
						notifySubscriberDeleted( person );
					} );
				} ) ).setHeader( "Manage" );

		grid.setItems( this.subscribersData );

		hint = new Div();
		hint.setText( "No invitation has been sent" );
		hint.getStyle().set( "padding", "var(--lumo-size-l)" )
				.set( "text-align", "center" ).set( "font-style", "italic" )
				.set( "color", "var(--lumo-contrast-70pct)" );

		add( hint, grid );
	}


	private void notifySubscriberDeleted( final Subscriber person ) {

		Notification.show(
				String.format( "Subscriber with email %s has been removed from the list.", person.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	private void refreshGrid() {

		if ( this.subscribersData.size() > 0 ) {
			grid.setVisible( true );
			hint.setVisible( false );
			grid.getDataProvider().refreshAll();
		} else {
			grid.setVisible( false );
			hint.setVisible( true );
		}
	}


	private void sendInvitation( Subscriber person ) {

		if ( person == null || this.subscribersData.contains( person ) ) {
			return;
		}
		this.subscribersData.add( person );
		this.refreshGrid();
	}


	private void removeInvitation( Subscriber subscriber ) {

		if ( subscriber == null ) {
			return;
		}
		this.service.delete( subscriber.getId() );
		this.subscribersData.remove( subscriber );
		this.refreshGrid();
	}

}
