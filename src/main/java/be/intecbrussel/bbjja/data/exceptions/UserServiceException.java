package be.intecbrussel.bbjja.data.exceptions;


import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException {

	private final HttpStatus status;


	public UserServiceException( final String message, final HttpStatus status ) {

		super( message );
		this.status = status;
	}


	public static UserServiceException notFound() {

		return new UserServiceException( "User NOT found!", HttpStatus.NOT_FOUND );
	}


	public static UserServiceException newPasswordRequired() {

		return new UserServiceException( "User new password required!", HttpStatus.BAD_REQUEST );
	}

	public static UserServiceException oldPasswordRequired() {

		return new UserServiceException( "User old password required!", HttpStatus.BAD_REQUEST );
	}

	public static UserServiceException usernameRequired() {

		return new UserServiceException( "Username is required!", HttpStatus.BAD_REQUEST );
	}

	public static UserServiceException passwordIncorrect() {

		return new UserServiceException( "User password is incorrect!", HttpStatus.BAD_REQUEST );
	}


	public static UserServiceException alreadyExists() {

		return new UserServiceException( "User already exists, cannot have duplicate.", HttpStatus.NOT_ACCEPTABLE );
	}


	public static UserServiceException badRequest() {

		return new UserServiceException( "An unknown exception! Please report this to the administrator.", HttpStatus.CONFLICT );
	}


	public HttpStatus getStatus() {

		return status;
	}

}
