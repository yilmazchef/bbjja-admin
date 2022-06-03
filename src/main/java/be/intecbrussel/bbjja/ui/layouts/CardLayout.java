package be.intecbrussel.bbjja.ui.layouts;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

@Tag("vaadin-card-layout")
public class CardLayout extends VerticalLayout {

	final HorizontalLayout header = new HorizontalLayout();
	final TextField title = new TextField("Header");
	final TextArea body = new TextArea("Body");
	final VerticalLayout image = new VerticalLayout();
	final Image img = new Image();
	final Button edit = new Button(VaadinIcon.EDIT.create());
	final VerticalLayout actions = new VerticalLayout();


	public CardLayout(@NotNull final String headerText,
			@NotNull final String bodyText,
			@NotNull @URL final String imageUrl) {

		title.setValue(headerText);
		title.getStyle()
				.set("width", "24%")
				.set("height", "320px")
				.set("padding", "0")
				.set("spacing", "0");
		body.getStyle()
				.set("width", "74%")
				.set("height", "320px")
				.set("padding", "0")
				.set("spacing", "0");
		body.setValue(bodyText);
		img.getStyle()
				.set("width", "100%")
				.set("height", "auto")
				.set("padding", "0")
				.set("spacing", "0");
		img.setSrc(imageUrl);
		img.setAlt(headerText);

		header.getStyle()
				.set("padding", "0")
				.set("spacing", "0");
		header.setAlignItems(FlexComponent.Alignment.STRETCH);
		header.add(title, body);

		image.setAlignItems(FlexComponent.Alignment.STRETCH);
		image.add(img);

		actions.setAlignItems(FlexComponent.Alignment.STRETCH);
		actions.add(edit);

		add(header, image);

	}

}
