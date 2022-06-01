package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@SpringComponent
public class SubscribersViewLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SubscribersViewLayout( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		setId( "subscribers-view-layout" );

		if ( subscriberService.count() > 0 ) {
			final var subscribersData = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();

			final var subscribersGrid = new Grid<>( Subscriber.class, false );
			subscribersGrid.setAllRowsVisible( true );
			subscribersGrid.addColumn( Subscriber :: getFirstName ).setHeader( "First Name" );
			subscribersGrid.addColumn( Subscriber :: getLastName ).setHeader( "Last Name" );
			subscribersGrid.addColumn( Subscriber :: getEmail ).setHeader( "Email" );

			subscribersGrid.setItems( subscribersData );
		}


	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
