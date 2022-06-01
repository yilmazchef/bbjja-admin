package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
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
public class SlidesDeleteLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public SlidesDeleteLayout( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {


		setId( "slides-delete-layout" );
		final var slidesData = slideService.list( PageRequest.of( 0, 25 ) ).toList();

		final var slidesGrid = new Grid<>( Slide.class, false );
		slidesGrid.setAllRowsVisible( true );
		slidesGrid.setWidthFull();
		slidesGrid.addColumn( Slide :: getTitle ).setHeader( "Title" );
		slidesGrid.addColumn( Slide :: getImageUrl ).setHeader( "Image URL" );
		slidesGrid.addColumn( Slide :: getPage ).setHeader( "Page" );

		slidesGrid.addColumn( new ComponentRenderer<>( Button :: new, ( deleteButton, slide ) -> {

			deleteButton.setIcon( new Icon( VaadinIcon.TRASH ) );
			deleteButton.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
			deleteButton.addClickListener( onDelete -> {

				if ( slide == null ) {
					return;
				}

				slideService.delete( slide.getId() );
				slidesData.remove( slide );

				if ( slidesData.size() > 0 ) {
					slidesGrid.setVisible( true );
					slidesGrid.getDataProvider().refreshAll();
				} else {
					slidesGrid.setVisible( false );
				}

				slidesGrid.setItems( slidesData );
				notifySlideDeleted( slide );

			} );


		} ) ).setHeader( "Manage" );

		slidesGrid.setItems( slidesData );

		add( slidesGrid );

	}


	private void notifySlideDeleted( final Slide slide ) {

		Notification.show( String.format( "Slide with title %s has been removed from the list.", slide.getTitle() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
