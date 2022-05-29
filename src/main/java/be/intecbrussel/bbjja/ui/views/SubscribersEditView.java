package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.PageRequest;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;

@PageTitle ( "BBJA | Subscribers (Edit)" )
@Route ( value = "subscribers/edit", layout = MainLayout.class )
@PermitAll
public class SubscribersEditView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final SubscriberService subscriberService;


	public SubscribersEditView( final AuthenticatedUser authenticatedUser, final SubscriberService subscriberService ) {

		this.authenticatedUser = authenticatedUser;
		this.subscriberService = subscriberService;

		setId( "subscribers-edit" );
		setViewContent( createContent() );

	}


	private Component createContent() {

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

		FlexBoxLayout content = new FlexBoxLayout( subscribersLayoutList.toArray( Component[] :: new ) );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifySubscriberDeleted( final Subscriber subscriber ) {

		Notification.show( String.format( "Subscriber with email %s has been removed from the list.", subscriber.getEmail() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
