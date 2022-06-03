package be.intecbrussel.bbjja.ui.layouts;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

@Tag ( "vaadin-modify-dialog" )
public class ModifyDialog extends Dialog {

	public ModifyDialog( final String headlineText,
	                     final ComponentEventListener< ClickEvent< Button > > onCancel,
	                     final ComponentEventListener< ClickEvent< Button > > onSave ) {

		getElement().setAttribute( "aria-label", "Add note" );

		final var header = new VerticalLayout();
		final var footer = new VerticalLayout();

		final var headline = new H2( headlineText );
		headline.addClassName( "draggable" );
		headline.getStyle().set( "margin", "0" ).set( "font-size", "1.5em" )
				.set( "font-weight", "bold" )
				.set( "cursor", "move" )
				.set( "padding", "var(--lumo-space-m) 0" )
				.set( "flex", "1" );

		header.add( headline );

		final var cancel = new Button( "Cancel", onCancel );
		final var save = new Button( "Add note", onSave );
		save.addThemeVariants( ButtonVariant.LUMO_PRIMARY );

		footer.add( cancel );
		footer.add( save );

		final var title = new TextField( "Title" );
		final var description = new TextArea( "Description" );
		final var fields = new VerticalLayout( title, description );
		fields.setSpacing( false );
		fields.setPadding( false );
		fields.setAlignItems( FlexComponent.Alignment.STRETCH );
		fields.getStyle().set( "width", "300px" ).set( "max-width", "100%" );

		add( fields );
		setModal( false );
		setDraggable( true );

	}


}
