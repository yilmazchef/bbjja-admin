package be.intecbrussel.bbjja.views;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.entity.Subscriber;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Direction;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.security.PermitAll;
import java.util.Objects;

import static java.lang.System.out;

@PageTitle ( "Home" )
@Route ( value = "home", layout = MainLayout.class )
@RouteAlias ( value = "", layout = MainLayout.class )
@PermitAll
public class HomeView extends VerticalLayout implements LocaleChangeObserver {

	@Autowired
	public HomeView( final AuthenticatedUser authenticatedUser, final SlideService slideService, final SubscriberService subscriberService ) {

		final var accordion = new Accordion();
		accordion.setSizeFull();

		final var newSlideLayout = new VerticalLayout();
		newSlideLayout.setSpacing( false );
		newSlideLayout.setPadding( false );

		final var newImageTitle = new TextField( "Title" );
		newImageTitle.setWidthFull();
		final var newImageURLField = new TextField( "Image URL" );
		newImageURLField.setWidthFull();
		final var newImageButton = new Button( "Add new slide", onClick -> {
			if ( ! newImageURLField.getValue().isEmpty() ) {
				final var newSlide = ( Slide ) new Slide().setImageUrl( newImageURLField.getValue() ).setTitle( newImageTitle.getValue() ).setIsActive( Boolean.TRUE );
				final var createdSlide = slideService.create( newSlide );
				notifySlideCreated( newSlide.getImageUrl() );
			}
		} );
		newImageButton.setWidthFull();
		newSlideLayout.addAndExpand( newImageTitle, newImageURLField, newImageButton );

		accordion.add( "Add New Slide", newSlideLayout );

		final var existingSlidesData = slideService.list( Sort.by( "dateModified" ) );
		for ( final var existingSlideItem : existingSlidesData ) {

			final var existingSlideItemLayout = new VerticalLayout();
			final var existingSlideItemImage = new Image( existingSlideItem.getImageUrl(), "BBJA Slide Image" );
			out.println( existingSlideItem.getImageUrl() );
			existingSlideItemImage.setWidthFull();
			final var existingSlideItemTitle = new H2( existingSlideItem.getTitle() );
			final var existingSlideItemDetails = new Paragraph( "Description, slogan, message, detailed content etc. is written here. 🤗" );
			existingSlideItemLayout.add( existingSlideItemImage, existingSlideItemTitle, existingSlideItemDetails );

			final var updateSlideLayout = new VerticalLayout();
			updateSlideLayout.setPadding( false );
			updateSlideLayout.setSpacing( false );
			final var updateSlideTitleField = new TextField( "Title" );
			updateSlideTitleField.setWidthFull();
			final var updateSlideImageUrlField = new TextField( "Image URL" );
			updateSlideImageUrlField.setWidthFull();

			final var updateButton = new Button( "Update Slide", onClick -> {
				if ( ! updateSlideImageUrlField.getValue().isEmpty() ) {
					final String oldURL = existingSlideItem.getImageUrl();
					final String newURL = updateSlideImageUrlField.getValue();
					final String newTitle = updateSlideTitleField.getValue();
					existingSlideItem.setImageUrl( newURL );
					existingSlideItem.setTitle( newTitle );
					final Slide updatedSlide = slideService.update( existingSlideItem );
					notifySlideUpdated( oldURL, newURL );
				}
			} );
			updateButton.setWidthFull();
			updateSlideLayout.addAndExpand( updateSlideImageUrlField, updateButton );

			existingSlideItemLayout.add( updateSlideLayout );

			// adding slideLayout and updateSlideLayout to parent layout.
			accordion.add( String.format( "View/Edit %s ", existingSlideItem.getTitle() ), existingSlideItemLayout );
		}

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

		final var sendInviteButton = new Button( "Send invite" );
		sendInviteButton.setWidthFull();
		sendInviteButton.addThemeVariants( ButtonVariant.LUMO_PRIMARY );
		sendInviteButton.addClickListener( e -> {

			if ( comboBox.getValue() == null || subscribersData.contains( comboBox.getValue() ) ) {
				return;
			}

			subscribersData.add( comboBox.getValue() );
			subscribersGrid.setVisible( true );
			subscribersGrid.getDataProvider().refreshAll();

			comboBox.setValue( null );
		} );

		final var invitationLayout = new HorizontalLayout( comboBox, sendInviteButton );
		invitationLayout.setFlexGrow( 1, comboBox );

		accordion.add( "Invite subscribers", invitationLayout );
		accordion.add( "View/Delete subscribers", subscribersGrid );

		add( accordion );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
	}


	private void notifySlideCreated( final String newImageURL ) {

		new Notification( String.format( "Slide image URL is added from %s", newImageURL ) ).open();
	}


	private void notifySlideUpdated( final String oldImageURL, final String newImageURL ) {

		new Notification( String.format( "Slide image URL is updated from %s to %s", oldImageURL, newImageURL ) ).open();
	}


	private void notifySubscriberDeleted( final Subscriber person ) {

		Notification.show(
				String.format( "Subscriber with email %s has been removed from the list.", person.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	@Override
	public void localeChange( LocaleChangeEvent event ) {

		if ( Objects.equals( event.getLocale().getLanguage(), "ar" ) ) {
			event.getUI().setDirection( Direction.RIGHT_TO_LEFT );
		} else {
			event.getUI().setDirection( Direction.LEFT_TO_RIGHT );
		}
	}


}
