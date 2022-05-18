package be.intecbrussel.bbjja.data.dto;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class NewPageRequest implements Serializable {

	@NotEmpty
	private String title;
	private String description;


	public NewPageRequest() {

	}


	public NewPageRequest( String title, String description ) {

		this.title = title;
		this.description = description;
	}


	public String getTitle() {

		return title;
	}


	public void setTitle( String title ) {

		this.title = title;
	}


	public String getDescription() {

		return description;
	}


	public void setDescription( String description ) {

		this.description = description;
	}


	@Override
	public boolean equals( Object o ) {

		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		NewPageRequest entity = ( NewPageRequest ) o;
		return Objects.equals( this.title, entity.title ) &&
				Objects.equals( this.description, entity.description );
	}


	@Override
	public int hashCode() {

		return Objects.hash( title, description );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"title = " + title + ", " +
				"description = " + description + ")";
	}

}
