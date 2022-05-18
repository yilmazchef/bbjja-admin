package be.intecbrussel.bbjja.data.entity;


import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table ( name = "slides" )
public class Slide extends AEntity {

	private String title;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String imageUrl;

	@ManyToOne
	@JoinColumn ( name = "page_id" )
	private Page page;


	public Slide() {

	}


	public Slide( final String title, final String imageUrl ) {

		this.title = title;
		this.imageUrl = imageUrl;
	}


	public String getTitle() {

		return title;
	}


	public void setTitle( final String title ) {

		this.title = title;
	}


	public Slide withTitle( final String title ) {

		this.setTitle( title );
		return this;
	}


	public String getImageUrl() {

		return imageUrl;
	}


	public void setImageUrl( final String imageUrl ) {

		this.imageUrl = imageUrl;
	}


	public Slide withImageUrl( final String imageUrl ) {

		this.setImageUrl( imageUrl );
		return this;
	}


	public Page getPage() {

		return page;
	}


	public void setPage( final Page page ) {

		this.page = page;
	}


	@Override
	public boolean equals( final Object o ) {

		if ( this == o ) {
			return true;
		}
		if ( ! ( o instanceof Slide ) ) {
			return false;
		}
		if ( ! super.equals( o ) ) {
			return false;
		}
		final Slide slide = ( Slide ) o;
		return getImageUrl().equals( slide.getImageUrl() );
	}


	@Override
	public int hashCode() {

		return Objects.hash( super.hashCode(), getImageUrl() );
	}


	@Override
	public String toString() {

		return this.getImageUrl();
	}

}