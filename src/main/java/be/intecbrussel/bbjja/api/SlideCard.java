package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Slide;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Tag ( "vaadin-slide-card-layout" )
public class SlideCard extends VerticalLayout {

	private final HorizontalLayout row1 = new HorizontalLayout();
	private final HorizontalLayout row2 = new HorizontalLayout();
	@Getter
	private final TextField title;
	@Getter
	private final TextArea description;
	@Getter
	private final Button manage;
	@Getter
	private final Image image;

	public enum OpenMode {
		CREATE,
		READ,
		UPDATE,
		DELETE
	}

	@Getter
	private final Slide bean;


	/*
	 * | title  | description  | manage |
	 * |             photo              |
	 * */
	public SlideCard( @NotNull final Slide slide, @NotNull final OpenMode mode ) {

		this.bean = slide;

		setMargin( false );
		setPadding( false );

		this.title = new TextField( "Title", ! slide.getTitle().isEmpty() ? slide.getTitle() : "", "" );
		this.description = new TextArea( "Description", ! slide.getDescription().isEmpty() ? slide.getDescription() : "", "" );
		this.image = new Image( ! slide.getImageUrl().isEmpty() ? slide.getImageUrl() : "./images/empty_plant.png", slide.getTitle() );
		this.manage = new Button();

		if ( mode == OpenMode.READ ) {
			this.title.setReadOnly( true );
			this.description.setReadOnly( true );
			this.image.setWidth( "100vw" );
			this.title.setWidth( "50vw" );
			this.description.setWidth( "50vw" );
			this.manage.setEnabled( false );
			this.manage.setVisible( false );
		}


		row1.add( title, description, manage );
		row2.add( image );

		add( row1, row2 );

	}


	public SlideCard() {

		this( new Slide(), OpenMode.CREATE );

	}

}
