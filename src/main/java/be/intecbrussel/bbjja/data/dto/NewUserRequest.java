package be.intecbrussel.bbjja.data.dto;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class NewUserRequest implements Serializable {

	@NotEmpty
	private String username;
	private String hashedPassword;


	public NewUserRequest() {

	}


	public NewUserRequest( String username, String hashedPassword ) {

		this.username = username;
		this.hashedPassword = hashedPassword;
	}


	public String getUsername() {

		return username;
	}


	public void setUsername( String username ) {

		this.username = username;
	}


	public NewUserRequest withUsername( final String username ) {

		this.setUsername( username );
		return this;
	}


	public String getHashedPassword() {

		return hashedPassword;
	}


	public void setHashedPassword( String hashedPassword ) {

		this.hashedPassword = hashedPassword;
	}


	public NewUserRequest withHashedPassword( final String hashedPassword ) {

		this.setHashedPassword( hashedPassword );
		return this;
	}


	@Override
	public boolean equals( Object o ) {

		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		NewUserRequest entity = ( NewUserRequest ) o;
		return Objects.equals( this.username, entity.username ) &&
				Objects.equals( this.hashedPassword, entity.hashedPassword );
	}


	@Override
	public int hashCode() {

		return Objects.hash( username, hashedPassword );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"username = " + username + ", " +
				"hashedPassword = " + hashedPassword + ")";
	}

}
