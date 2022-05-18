package be.intecbrussel.bbjja.data.dto;


import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class NewSlideRequest implements Serializable {

	private String title;
	@URL
	private String imageUrl;
	private UUID pageId;


	public NewSlideRequest() {

	}


	public NewSlideRequest( String title, String imageUrl, UUID pageId ) {

		this.title = title;
		this.imageUrl = imageUrl;
		this.pageId = pageId;
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
		NewSlideRequest entity = ( NewSlideRequest ) o;
		return Objects.equals( this.title, entity.title ) &&
				Objects.equals( this.imageUrl, entity.imageUrl ) &&
				Objects.equals( this.pageId, entity.pageId );
	}


	@Override
	public int hashCode() {

		return Objects.hash( title, imageUrl, pageId );
	}


	@Override
	public String toString() {

		return getClass().getSimpleName() + "(" +
				"title = " + title + ", " +
				"imageUrl = " + imageUrl + ", " +
				"pageId = " + pageId + ")";
	}

}
