package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Slide;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;

@Tag ( "vaadin-slide-card-layout" )
public class SlideCard extends VerticalLayout {

	@Getter
	private final Slide bean;


	/*
	 * | title  | description  | manage |
	 * |             photo              |
	 * */
	public SlideCard( @NotNull final Slide slide ) {

		bean = slide;

		final var title = new TextField( "Title", ! slide.getTitle().isEmpty() ? slide.getTitle() : "", "" );
		title.addValueChangeListener( onTitleChange -> {
			final var oldTitle = onTitleChange.getOldValue();
			final var newTitle = onTitleChange.getValue();

			if ( ! newTitle.isEmpty() && ! oldTitle.equalsIgnoreCase( newTitle ) ) {
				this.bean.setTitle( newTitle );
			}
		} );
		final var description = new TextArea( "Description", ! slide.getDescription().isEmpty() ? slide.getDescription() : "", "" );
		description.addValueChangeListener( onDescriptionChange -> {
			final var oldTitle = onDescriptionChange.getOldValue();
			final var newTitle = onDescriptionChange.getValue();

			if ( ! newTitle.isEmpty() && ! oldTitle.equalsIgnoreCase( newTitle ) ) {
				this.bean.setTitle( newTitle );
			}
		} );
		final var src = new Image( ! slide.getImageUrl().isEmpty() ? slide.getImageUrl() : "./images/empty_plant.png", slide.getTitle() );
		src.getStyle()
				.set( "width", "100%" )
				.set( "height", "auto" )
				.set( "margin", "0" );
		final var image = new Button( src );
		image.addClickListener( onImageChange -> {
			final var newImage = new ModifyDialog(
					MessageFormat.format( "Update image URL for {imgAlt}", slide.getTitle() ),
					onCancel -> {
						Notification.show( "update request is cancelled!" ).open();
					},
					onSave -> {
						Notification.show( "update request is complete." ).open();
					}
			);
			newImage.open();
			// TODO: update image using backend services.
		} );

		add(
				title,
				description,
				image
		);

	}

}
