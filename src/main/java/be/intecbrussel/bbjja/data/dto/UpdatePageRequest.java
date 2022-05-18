package be.intecbrussel.bbjja.data.dto;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UpdatePageRequest implements Serializable {

	private UUID id;
	private Boolean isActive;
	@NotEmpty
	private String title;
	private String description;


	public UpdatePageRequest() {

	}


	public UpdatePageRequest( UUID id, Boolean isActive, String title, String description ) {

		this.id = id;
		this.isActive = isActive;
		this.title = title;
		this.description = description;
	}


	public UUID getId() {

		return id;
	}


	public void setId( UUID id ) {

		this.id = id;
	}


	public Boolean getIsActive() {

		return isActive;
	}


	public void setIsActive( Boolean isActive ) {

		this.isActive = isActive;
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
		UpdatePageRequest entity = ( UpdatePageRequest ) o;
		return Objects.equals( this.id, entity.id ) &&
				Objects.equals( this.isActive, entity.isActive ) &&
				Objects.equals( this.title, entity.title ) &&
				Objects.equals( this.description, entity.description );
	}


	@Override
	public int hashCode() {

		return Objects.hash( id, isActive, title, description );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"isActive = " + isActive + ", " +
				"title = " + title + ", " +
				"description = " + description + ")";
	}

}
