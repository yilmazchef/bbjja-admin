package be.intecbrussel.bbjja.ui.media;


import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import lombok.Getter;

@Getter
@Tag ( "video" )
public class Video extends VerticalLayout {

	private final String src;


	public Video( final String src ) {

		this.src = src;

		getElement().setAttribute( "controls", "" );
		getElement().setAttribute( "width", "100%" );
		getElement().setAttribute( "height", "100%" );
		getElement().setAttribute( "preload", "auto" );
		getElement().setAttribute( "autoplay", "true" );

		final var sourceElement = new Element( "source" );
		sourceElement.setAttribute( "src", this.getSrc() );
		sourceElement.setAttribute( "type", "video/mp4" );

		getElement().setText( " Your browser does not support the video tag." );

		getElement().appendChild( sourceElement );

	}

}
