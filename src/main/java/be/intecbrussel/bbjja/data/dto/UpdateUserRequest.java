package be.intecbrussel.bbjja.data.dto;


import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UpdateUserRequest implements Serializable {

	private UUID id;
	@NotEmpty
	private String username;
	@NotEmpty
	private String name;
	private String hashedPassword;
	@URL
	private String profilePictureUrl;


	public UpdateUserRequest() {

	}


	public UpdateUserRequest( UUID id, String username, String name, String hashedPassword, String profilePictureUrl ) {

		this.id = id;
		this.username = username;
		this.name = name;
		this.hashedPassword = hashedPassword;
		this.profilePictureUrl = profilePictureUrl;
	}


	public UUID getId() {

		return id;
	}


	public void setId( UUID id ) {

		this.id = id;
	}


	public UpdateUserRequest withId( final UUID id ) {

		this.setId( id );
		return this;
	}


	public String getUsername() {

		return username;
	}


	public void setUsername( String username ) {

		this.username = username;
	}


	public UpdateUserRequest withUsername( final String username ) {

		this.setUsername( username );
		return this;
	}


	public String getName() {

		return name;
	}


	public void setName( String name ) {

		this.name = name;
	}


	public UpdateUserRequest withName( final String name ) {

		this.setName( name );
		return this;
	}


	public String getHashedPassword() {

		return hashedPassword;
	}


	public void setHashedPassword( String hashedPassword ) {

		this.hashedPassword = hashedPassword;
	}


	public UpdateUserRequest withHashedPassword( final String hashedPassword ) {

		this.setHashedPassword( hashedPassword );
		return this;
	}


	public String getProfilePictureUrl() {

		return profilePictureUrl;
	}


	public void setProfilePictureUrl( String profilePictureUrl ) {

		this.profilePictureUrl = profilePictureUrl;
	}


	public UpdateUserRequest withProfilePictureUrl( final String profilePictureUrl ) {

		this.setProfilePictureUrl( profilePictureUrl );
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
		UpdateUserRequest entity = ( UpdateUserRequest ) o;
		return Objects.equals( this.id, entity.id ) &&
				Objects.equals( this.username, entity.username ) &&
				Objects.equals( this.name, entity.name ) &&
				Objects.equals( this.hashedPassword, entity.hashedPassword ) &&
				Objects.equals( this.profilePictureUrl, entity.profilePictureUrl );
	}


	@Override
	public int hashCode() {

		return Objects.hash( id, username, name, hashedPassword, profilePictureUrl );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"username = " + username + ", " +
				"name = " + name + ", " +
				"hashedPassword = " + hashedPassword + ", " +
				"profilePictureUrl = " + profilePictureUrl + ")";
	}

}
