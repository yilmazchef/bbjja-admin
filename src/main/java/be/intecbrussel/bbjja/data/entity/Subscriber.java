package be.intecbrussel.bbjja.data.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@Table ( name = "subscribers" )
public class Subscriber extends AEntity {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@Column ( nullable = false, unique = true )
	@Email
	private String email;


	public Subscriber() {

	}


	public Subscriber( final String email ) {

		this.email = email;
	}


	public Subscriber( final String firstName, final String lastName, final String email ) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public String getEmail() {

		return email;
	}


	public void setEmail( final String email ) {

		this.email = email;
	}


	public Subscriber withEmail( final String email ) {

		this.setEmail( email );
		return this;
	}


	public String getFirstName() {

		return firstName;
	}


	public void setFirstName( final String firstName ) {

		this.firstName = firstName;
	}


	public Subscriber withFirstName( final String firstName ) {

		this.setFirstName( firstName );
		return this;
	}


	public String getLastName() {

		return lastName;
	}


	public void setLastName( final String lastName ) {

		this.lastName = lastName;
	}


	public Subscriber withLastName( final String lastName ) {

		this.setLastName( lastName );
		return this;
	}


	@Override
	public boolean equals( final Object o ) {

		if ( this == o ) {
			return true;
		}
		if ( ! ( o instanceof Subscriber ) ) {
			return false;
		}
		if ( ! super.equals( o ) ) {
			return false;
		}
		final Subscriber that = ( Subscriber ) o;
		return getEmail().equals( that.getEmail() );
	}


	@Override
	public int hashCode() {

		return Objects.hash( super.hashCode(), getEmail() );
	}


	@Override
	public String toString() {

		return this.getEmail();
	}

}