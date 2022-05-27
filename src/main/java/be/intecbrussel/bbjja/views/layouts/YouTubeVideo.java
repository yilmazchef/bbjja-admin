package be.intecbrussel.bbjja.views.layouts;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import lombok.Getter;

@Tag ( "iframe" )
@Getter
public class YouTubeVideo extends Component {

	private final String url;
	private final Boolean autoPlay;
	private final Boolean hasControls;
	private final Boolean isMuted;
	private final Boolean isLoop;


	public YouTubeVideo( final String url ) {

		this( url, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE );
	}


	public YouTubeVideo( final String url, final Boolean autoPlay, final Boolean hasControls, final Boolean isMuted, final Boolean isLoop ) {

		this.url = url;
		this.autoPlay = autoPlay;
		this.hasControls = hasControls;
		this.isMuted = isMuted;
		this.isLoop = isLoop;

		getElement().setAttribute( "width", "100%" );
		getElement().setAttribute( "height", "auto" );

		if ( autoPlay ) {
			getElement().setAttribute( "autoplay", "1" );
		}

		if ( isLoop ) {
			getElement().setAttribute( "loop", "1" );
		}

		if ( hasControls ) {
			getElement().setAttribute( "controls", "1" );
		}

		if ( isMuted ) {
			getElement().setAttribute( "mute", "1" );
		}


		getElement().setAttribute( "src", url );

	}

}
