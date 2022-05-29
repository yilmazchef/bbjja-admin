package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
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

@PageTitle ( "BBJA | Slides" )
@Route ( value = "slides", layout = MainLayout.class )
@PermitAll
public class SlidesQuickView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final SlideService slideService;


	public SlidesQuickView( final AuthenticatedUser authenticatedUser, final SlideService slideService ) {

		this.authenticatedUser = authenticatedUser;
		this.slideService = slideService;

		setId( "slides-quick" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var slidesData = slideService.list( PageRequest.of( 1, 25 ) ).toList();

		final var slidesGrid = new Grid<>( Slide.class, false );
		slidesGrid.setAllRowsVisible( true );
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

		FlexBoxLayout content = new FlexBoxLayout( slidesGrid );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
	}


	private void notifySlideDeleted( final Slide slide ) {

		Notification.show( String.format( "Slide with title %s has been removed from the list.", slide.getTitle() ), 3000, Notification.Position.TOP_CENTER ).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
