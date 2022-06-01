package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@SpringComponent
@Tag( "subscribers-delete-layout" )
public class SubscribersDeleteLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SubscribersDeleteLayout( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		if ( subscriberService.count() > 0 ) {

			final var subscribers = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();

			final var grid = new Grid<>( Subscriber.class, false );
			grid.setAllRowsVisible( true );
			grid.addColumn( Subscriber :: getFirstName ).setHeader( "First Name" );
			grid.addColumn( Subscriber :: getLastName ).setHeader( "Last Name" );
			grid.addColumn( Subscriber :: getEmail ).setHeader( "Email" );

			grid.addColumn( new ComponentRenderer<>( Button :: new, ( delete, subscriber ) -> {

				delete.setIcon( new Icon( VaadinIcon.TRASH ) );
				delete.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
				delete.addClickListener( onDelete -> {

					if ( subscriber == null ) {
						return;
					}

					subscriberService.delete( subscriber.getId() );
					subscribers.remove( subscriber );

					if ( subscribers.size() > 0 ) {
						grid.setVisible( true );
						grid.getDataProvider().refreshAll();
					} else {
						grid.setVisible( false );
					}

					grid.setItems( subscribers );
					notifySubscriberDeleted( subscriber );

				} );


			} ) ).setHeader( "Manage" );

			grid.setItems( subscribers );

			add( grid );
		}


	}


	private void notifySubscriberDeleted( final Subscriber person ) {

		Notification.show( String.format( "Subscriber with email %s has been removed from the list.", person.getEmail() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
