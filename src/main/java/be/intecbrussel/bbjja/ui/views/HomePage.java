package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Slide;
import be.intecbrussel.bbjja.data.service.SlideService;
import be.intecbrussel.bbjja.data.service.SubscriberService;
import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import be.intecbrussel.bbjja.ui.layouts.ValidationMessage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle ( "BBJJA | Home" )
@Route ( value = "home", layout = MainLayout.class )
@RouteAlias ( value = "", layout = MainLayout.class )
@AnonymousAllowed
@Tag ( "home-page" )
public class HomePage extends VerticalLayout {


	@Autowired
	public HomePage( final SlideService slideService, final SubscriberService subscriberService ) {

		final var titleValidationMessage = new ValidationMessage();
		final var imageUrlValidationMessage = new ValidationMessage();
		final var descriptionValidationMessage = new ValidationMessage();

		final var grid = new Grid<>( Slide.class, false );
		grid.setSizeFull();
		final var titleColumn = grid
				.addColumn( Slide :: getTitle )
				.setHeader( "Title" )
				.setAutoWidth( true )
				.setFlexGrow( 0 );
		final var imageUrlColumn = grid
				.addComponentColumn( slide -> new Image( slide.getImageUrl(), slide.getTitle() ) )
				.setHeader( "Image URL" )
				.setAutoWidth( true )
				.setResizable( true )
				.setFlexGrow( 2 );
		final var descriptionColumn = grid
				.addColumn( Slide :: getDescription )
				.setHeader( "Description" )
				.setAutoWidth( true )
				.setFlexGrow( 0 );

		final var binder = new Binder<>( Slide.class );
		final var editor = grid.getEditor();
		editor.setBinder( binder );

		final var titleField = new TextField();
		titleField.setWidthFull();
		addSlideCloseHandler( titleField, editor );
		binder.forField( titleField )
				.asRequired( "Title must not be empty" )
				.withStatusLabel( titleValidationMessage )
				.bind( Slide :: getTitle, Slide :: setTitle );
		titleColumn.setEditorComponent( titleField );

		final var imageUrlField = new TextField();
		imageUrlField.setWidthFull();
		addSlideCloseHandler( imageUrlField, editor );
		binder.forField( imageUrlField )
				.asRequired( "Image URL must NOT be empty!" )
				.withStatusLabel( imageUrlValidationMessage )
				.bind( Slide :: getImageUrl, Slide :: setImageUrl );
		imageUrlColumn.setEditorComponent( imageUrlField );

		final var descriptionField = new TextField();
		descriptionField.setWidthFull();
		addSlideCloseHandler( descriptionField, editor );
		binder.forField( descriptionField )
				.asRequired( "Description must not be empty" )
				.withStatusLabel( descriptionValidationMessage )
				.bind( Slide :: getDescription, Slide :: setDescription );
		descriptionColumn.setEditorComponent( descriptionField );

		grid.addItemDoubleClickListener( e -> {
			editor.editItem( e.getItem() );
			Component editorComponent = e.getColumn().getEditorComponent();
			if ( editorComponent instanceof Focusable ) {
				( ( Focusable< ? > ) editorComponent ).focus();
			}
		} );

		editor.addCancelListener( e -> {
			titleValidationMessage.setText( "" );
			imageUrlValidationMessage.setText( "" );
			descriptionValidationMessage.setText( "" );
		} );

		final var slideData = slideService.list();
		grid.setItems( slideData );

		getThemeList().clear();
		getThemeList().add( "spacing-s" );
		add( grid, titleValidationMessage, imageUrlValidationMessage, descriptionValidationMessage );

	}


	private static void addSlideCloseHandler( Component textField, Editor< Slide > editor ) {

		textField
				.getElement()
				.addEventListener( "keydown", e -> editor.cancel() )
				.setFilter( "event.code === 'Escape'" );
	}

}
