package be.intecbrussel.bbjja.data.exceptions;


import org.springframework.http.HttpStatus;

public class SlideServiceException extends RuntimeException {

	private final HttpStatus status;


	public SlideServiceException( final String message, final HttpStatus status ) {

		super( message );
		this.status = status;
	}


	public static SlideServiceException notFound() {

		return new SlideServiceException( "Slide NOT found!", HttpStatus.NOT_FOUND );
	}


	public static SlideServiceException titleRequired() {

		return new SlideServiceException( "Slide title is required!", HttpStatus.BAD_REQUEST );
	}


	public static SlideServiceException alreadyExists() {

		return new SlideServiceException( "Slide already exists, cannot have duplicate.", HttpStatus.NOT_ACCEPTABLE );
	}


	public static SlideServiceException badRequest() {

		return new SlideServiceException( "An unknown exception! Please report this to the administrator.", HttpStatus.CONFLICT );
	}


	public HttpStatus getStatus() {

		return status;
	}

}
