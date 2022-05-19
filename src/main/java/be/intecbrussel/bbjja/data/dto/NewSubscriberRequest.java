package be.intecbrussel.bbjja.data.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class NewSubscriberRequest implements Serializable {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Email
	private String email;


	public NewSubscriberRequest() {

	}


	public NewSubscriberRequest( String firstName, String lastName, String email ) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public String getFirstName() {

		return firstName;
	}


	public void setFirstName( String firstName ) {

		this.firstName = firstName;
	}


	public NewSubscriberRequest withFirstName( final String firstName ) {

		this.setFirstName( firstName );
		return this;
	}


	public String getLastName() {

		return lastName;
	}


	public void setLastName( String lastName ) {

		this.lastName = lastName;
	}


	public NewSubscriberRequest withLastName( final String lastName ) {

		this.setLastName( lastName );
		return this;
	}


	public String getEmail() {

		return email;
	}


	public void setEmail( String email ) {

		this.email = email;
	}

	public NewSubscriberRequest withEmail(final String email){
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
		NewSubscriberRequest entity = ( NewSubscriberRequest ) o;
		return Objects.equals( this.firstName, entity.firstName ) &&
				Objects.equals( this.lastName, entity.lastName ) &&
				Objects.equals( this.email, entity.email );
	}


	@Override
	public int hashCode() {

		return Objects.hash( firstName, lastName, email );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"firstName = " + firstName + ", " +
				"lastName = " + lastName + ", " +
				"email = " + email + ")";
	}

}
