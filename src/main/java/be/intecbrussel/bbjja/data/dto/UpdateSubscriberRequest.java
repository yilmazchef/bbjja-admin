package be.intecbrussel.bbjja.data.dto;


import org.hibernate.sql.Update;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UpdateSubscriberRequest implements Serializable {

	private UUID id;
	private Boolean isActive;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Email
	private String email;


	public UpdateSubscriberRequest() {

	}


	public UpdateSubscriberRequest( final UUID id, final Boolean isActive, final String firstName, final String lastName, final String email ) {

		this.id = id;
		this.isActive = isActive;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public UUID getId() {

		return id;
	}


	public void setId( UUID id ) {

		this.id = id;
	}


	public UpdateSubscriberRequest withId( final UUID id ) {

		this.setId( id );
		return this;
	}


	public Boolean getIsActive() {

		return isActive;
	}


	public void setIsActive( Boolean isActive ) {

		this.isActive = isActive;
	}


	public UpdateSubscriberRequest withActive( final Boolean isActive ) {

		this.setIsActive( isActive );
		return this;
	}


	public String getFirstName() {

		return firstName;
	}


	public void setFirstName( String firstName ) {

		this.firstName = firstName;
	}


	public UpdateSubscriberRequest withFirstName( final String firstName ) {

		this.setFirstName( firstName );
		return this;
	}


	public String getLastName() {

		return lastName;
	}


	public void setLastName( String lastName ) {

		this.lastName = lastName;
	}


	public UpdateSubscriberRequest withLastName( final String lastName ) {

		this.setLastName( lastName );
		return this;
	}


	public String getEmail() {

		return email;
	}


	public void setEmail( String email ) {

		this.email = email;
	}


	public UpdateSubscriberRequest withEmail( final String email ) {

		this.setEmail( email );
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
		UpdateSubscriberRequest entity = ( UpdateSubscriberRequest ) o;
		return Objects.equals( this.id, entity.id ) &&
				Objects.equals( this.isActive, entity.isActive ) &&
				Objects.equals( this.firstName, entity.firstName ) &&
				Objects.equals( this.lastName, entity.lastName ) &&
				Objects.equals( this.email, entity.email );
	}


	@Override
	public int hashCode() {

		return Objects.hash( id, isActive, firstName, lastName, email );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"isActive = " + isActive + ", " +
				"firstName = " + firstName + ", " +
				"lastName = " + lastName + ", " +
				"email = " + email + ")";
	}

}
