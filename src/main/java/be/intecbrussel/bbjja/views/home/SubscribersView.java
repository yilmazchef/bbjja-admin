package be.intecbrussel.bbjja.views.home;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import javax.annotation.security.RolesAllowed;

@PageTitle ( "Subscribers" )
@Route ( value = "subscribers", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class SubscribersView extends VerticalLayout {


	@Autowired
	public SubscribersView( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		final var subscribersData = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();

		final var subscribersGrid = new Grid<>( Subscriber.class, false );
		subscribersGrid.setAllRowsVisible( true );
		subscribersGrid.addColumn( Subscriber :: getFirstName ).setHeader( "First Name" );
		subscribersGrid.addColumn( Subscriber :: getLastName ).setHeader( "Last Name" );
		subscribersGrid.addColumn( Subscriber :: getEmail ).setHeader( "Email" );

		subscribersGrid.addColumn(
				new ComponentRenderer<>( Button :: new, ( deleteButton, subscriber ) -> {

					deleteButton.setIcon( new Icon( VaadinIcon.TRASH ) );
					deleteButton.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
					deleteButton.addClickListener( onDelete -> {

						if ( subscriber == null ) {
							return;
						}

						subscriberService.delete( subscriber.getId() );
						subscribersData.remove( subscriber );

						if ( subscribersData.size() > 0 ) {
							subscribersGrid.setVisible( true );
							subscribersGrid.getDataProvider().refreshAll();
						} else {
							subscribersGrid.setVisible( false );
						}

						subscribersGrid.setItems( subscribersData );
						notifySubscriberDeleted( subscriber );

					} );


				} ) ).setHeader( "Manage" );

		subscribersGrid.setItems( subscribersData );

		final var comboBox = new ComboBox< Subscriber >();
		comboBox.setItems( subscribersData );
		comboBox.setWidthFull();
		comboBox.setItemLabelGenerator( s -> String.format( "%s %s | %s", s.getFirstName(), s.getLastName(), s.getEmail() ) );

		final var button = new Button( "Send invite" );
		button.addThemeVariants( ButtonVariant.LUMO_PRIMARY );
		button.addClickListener( e -> {

			if ( comboBox.getValue() == null || subscribersData.contains( comboBox.getValue() ) ) {
				return;
			}

			subscribersData.add( comboBox.getValue() );
			subscribersGrid.setVisible( true );
			subscribersGrid.getDataProvider().refreshAll();

			comboBox.setValue( null );
		} );

		final var invitationLayout = new HorizontalLayout( comboBox, button );
		invitationLayout.setFlexGrow( 1, comboBox );

		add( invitationLayout, subscribersGrid );
	}


	private void notifySubscriberDeleted( final Subscriber person ) {

		Notification.show(
				String.format( "Subscriber with email %s has been removed from the list.", person.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


}
