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
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.PageRequest;

import javax.annotation.security.PermitAll;

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

		final var subscribersData = subscriberService.list( PageRequest.of( 1, 25 ) ).toList();

		final var subscribersGrid = new Grid<>( Subscriber.class, false );
		subscribersGrid.setAllRowsVisible( true );
		subscribersGrid.addColumn( Subscriber :: getFirstName ).setHeader( "First Name" );
		subscribersGrid.addColumn( Subscriber :: getLastName ).setHeader( "Last Name" );
		subscribersGrid.addColumn( Subscriber :: getEmail ).setHeader( "Email" );

		subscribersGrid.addColumn( new ComponentRenderer<>( Button :: new, ( deleteButton, subscriber ) -> {

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

		FlexBoxLayout content = new FlexBoxLayout( subscribersGrid );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifySubscriberDeleted( final Subscriber person ) {

		Notification.show( String.format( "Subscriber with email %s has been removed from the list.", person.getEmail() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
