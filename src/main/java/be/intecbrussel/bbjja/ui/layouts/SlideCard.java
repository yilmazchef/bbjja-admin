package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Slide;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;

@Tag ( "vaadin-slide-card-layout" )
public class SlideCard extends VerticalLayout {

	@Getter
	private final Slide slide;


	/*
	 * | title  | description  | manage |
	 * |             photo              |
	 * */
	public SlideCard( @NotNull final Slide slide ) {

		this.slide = slide;

		final var header = new HorizontalLayout();

		final var title = new TextField( "Title", ! this.slide.getTitle().isEmpty() ? this.slide.getTitle() : "", "" );
		title.getStyle()
			.set("width", "24%")
			.set("height", "auto")
			.set("padding", "0")
			.set("spacing", "0");
		title.addValueChangeListener( onTitleChange -> {
			final var oldTitle = onTitleChange.getOldValue();
			final var newTitle = onTitleChange.getValue();

			if ( ! newTitle.isEmpty() && ! oldTitle.equalsIgnoreCase( newTitle ) ) {
				this.slide.setTitle( newTitle );
			}
		} );
		final var description = new TextArea( "Description", ! this.slide.getDescription().isEmpty() ? this.slide.getDescription() : "", "" );
		description.getStyle()
			.set("width", "74%")
			.set("height", "auto")
			.set("padding", "0")
			.set("spacing", "0");
		description.addValueChangeListener( onDescriptionChange -> {
			final var oldTitle = onDescriptionChange.getOldValue();
			final var newTitle = onDescriptionChange.getValue();

			if ( ! newTitle.isEmpty() && ! oldTitle.equalsIgnoreCase( newTitle ) ) {
				this.slide.setTitle( newTitle );
			}
		} );
		final var img = new Image( ! this.slide.getImageUrl().isEmpty() ? this.slide.getImageUrl() : "./images/empty_plant.png", this.slide.getTitle() );
		img.getStyle()
				.set( "width", "100%" )
				.set( "height", "auto" )
				.set("padding", "0")
				.set("spacing","0");
		final var edit = new Button( VaadinIcon.EDIT.create() );
		edit.addClickListener( onImageChange -> {
			final var newImage = new ModifyDialog(
					MessageFormat.format( "Update image URL for {title}", this.slide.getTitle() ),
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

		header.add(
				title,
				description
		);


		final var image = new VerticalLayout();
		image.add(
			img, edit
		);

		add(header, image);


	}

}
