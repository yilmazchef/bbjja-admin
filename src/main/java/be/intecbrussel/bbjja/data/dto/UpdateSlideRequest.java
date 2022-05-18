package be.intecbrussel.bbjja.data.dto;


import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UpdateSlideRequest implements Serializable {

	private UUID id;
	private Boolean isActive;
	private String title;
	@URL
	private String imageUrl;
	private UUID pageId;


	public UpdateSlideRequest() {

	}


	public UpdateSlideRequest( UUID id, Boolean isActive, String title, String imageUrl, UUID pageId ) {

		this.id = id;
		this.isActive = isActive;
		this.title = title;
		this.imageUrl = imageUrl;
		this.pageId = pageId;
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


	public String getImageUrl() {

		return imageUrl;
	}


	public void setImageUrl( String imageUrl ) {

		this.imageUrl = imageUrl;
	}


	public UUID getPageId() {

		return pageId;
	}


	public void setPageId( UUID pageId ) {

		this.pageId = pageId;
	}


	@Override
	public boolean equals( Object o ) {

		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		UpdateSlideRequest entity = ( UpdateSlideRequest ) o;
		return Objects.equals( this.id, entity.id ) &&
				Objects.equals( this.isActive, entity.isActive ) &&
				Objects.equals( this.title, entity.title ) &&
				Objects.equals( this.imageUrl, entity.imageUrl ) &&
				Objects.equals( this.pageId, entity.pageId );
	}


	@Override
	public int hashCode() {

		return Objects.hash( id, isActive, title, imageUrl, pageId );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"isActive = " + isActive + ", " +
				"title = " + title + ", " +
				"imageUrl = " + imageUrl + ", " +
				"pageId = " + pageId + ")";
	}

}
