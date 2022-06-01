package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

@SpringComponent
@Tag ( "subscribers-update-layout" )
public class SubscribersUpdateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SubscribersUpdateLayout( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		if ( subscriberService.count() > 0 ) {
			final var subscribers = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();
			final var layouts = new ArrayList< VerticalLayout >();

			for ( final Subscriber s : subscribers ) {

				final var layout = new VerticalLayout();

				final var email = new TextField( "Email" );
				email.setValue( s.getEmail() );
				final var firstName = new TextField( "First name" );
				final var lastName = new TextField( "Last name" );

				final var form = new FormLayout();
				form.add( email, firstName, lastName );
				form.setResponsiveSteps(
						// Use one column by default
						new FormLayout.ResponsiveStep( "0", 1 ),
						// Use two columns, if layout's width exceeds 500px
						new FormLayout.ResponsiveStep( "500px", 2 ) );
				// Stretch the username field over 2 columns
				form.setColspan( email, 2 );

				final var update = new Button( "Submit", onClick -> {
					final var savedSubscriber = subscriberService.update( s );
					new Notification( savedSubscriber.toString(), 3000 ).open();
				} );

				form.add( update );

				layout.add( form );

				layouts.add( layout );

			}

			for ( final var l : layouts ) {
				add( l );
			}


		}


	}


	private void notifySubscriberDeleted( final Subscriber subscriber ) {

		Notification.show( String.format( "Subscriber with email %s has been removed from the list.", subscriber.getEmail() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
