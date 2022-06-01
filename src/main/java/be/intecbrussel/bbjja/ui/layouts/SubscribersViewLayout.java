package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@SpringComponent
@Tag ( "subscribers-view-layout" )
public class SubscribersViewLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SubscribersViewLayout( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		if ( subscriberService.count() > 0 ) {
			final var subscribers = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();

			final var grid = new Grid<>( Subscriber.class, false );
			grid.setAllRowsVisible( true );
			grid.addColumn( Subscriber :: getFirstName ).setHeader( "First Name" );
			grid.addColumn( Subscriber :: getLastName ).setHeader( "Last Name" );
			grid.addColumn( Subscriber :: getEmail ).setHeader( "Email" );

			grid.setItems( subscribers );

			add( grid );

		}


	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
