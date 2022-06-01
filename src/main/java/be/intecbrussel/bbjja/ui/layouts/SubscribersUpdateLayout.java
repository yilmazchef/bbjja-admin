package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
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
public class SubscribersUpdateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SubscribersUpdateLayout( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		setId( "subscribers-update-layout" );

		final var subscribersDataList = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();
		final var subscribersLayoutList = new ArrayList< Component >();

		for ( final Subscriber subscriber : subscribersDataList ) {

			final var usernameField = new TextField( "Email" );
			usernameField.setValue( subscriber.getEmail() );
			final var firstNameField = new TextField( "First name" );
			final var lastNameField = new TextField( "Last name" );

			final var updateUserLayout = new FormLayout();
			updateUserLayout.add( usernameField, firstNameField, lastNameField );
			updateUserLayout.setResponsiveSteps(
					// Use one column by default
					new FormLayout.ResponsiveStep( "0", 1 ),
					// Use two columns, if layout's width exceeds 500px
					new FormLayout.ResponsiveStep( "500px", 2 ) );
			// Stretch the username field over 2 columns
			updateUserLayout.setColspan( usernameField, 2 );

			final var updateUserButton = new Button( "Submit", onClick -> {
				final var savedSubscriber = subscriberService.update( subscriber );
				new Notification( savedSubscriber.toString(), 3000 ).open();
			} );

			updateUserLayout.add( updateUserButton );

			subscribersLayoutList.add( updateUserLayout );

		}

		for ( final Component component : subscribersLayoutList ) {
			add( component );
		}

	}


	private void notifySubscriberDeleted( final Subscriber subscriber ) {

		Notification.show( String.format( "Subscriber with email %s has been removed from the list.", subscriber.getEmail() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
