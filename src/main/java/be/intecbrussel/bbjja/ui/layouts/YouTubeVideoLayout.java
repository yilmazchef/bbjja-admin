package be.intecbrussel.bbjja.ui.layouts;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import lombok.Getter;
import lombok.Setter;

@Tag ( "iframe" )
@Getter
public class YouTubeVideoLayout extends Component {

	private final String url;
	private final Boolean autoPlay;
	private final Boolean hasControls;
	private final Boolean isMuted;
	private final Boolean isLoop;

	@Getter
	@Setter
	private String width;

	@Getter
	@Setter
	private String height;


	public YouTubeVideoLayout( final String url ) {

		this( url, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE );
	}


	public YouTubeVideoLayout( final String url, final Boolean autoPlay, final Boolean hasControls, final Boolean isMuted, final Boolean isLoop ) {

		setId( "youtube-video-layout" );

		this.width = "100%";
		this.height = "100%";

		this.url = url;
		this.autoPlay = autoPlay;
		this.hasControls = hasControls;
		this.isMuted = isMuted;
		this.isLoop = isLoop;

		getElement().setAttribute( "width", this.getWidth() );
		getElement().setAttribute( "height", this.getHeight() );

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


	public void setSize( final String width, final String height ) {

		this.setWidth( width );
		this.setHeight( height );

		getElement().setAttribute( "width", this.getWidth() );
		getElement().setAttribute( "height", this.getHeight() );
	}


	public void setSizeFull() {

		this.setWidth( "100%" );
		this.setHeight( "100%" );

		getElement().setAttribute( "width", this.getWidth() );
		getElement().setAttribute( "height", this.getHeight() );
	}

}
