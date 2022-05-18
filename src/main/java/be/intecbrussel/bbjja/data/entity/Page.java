package be.intecbrussel.bbjja.data.entity;


import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table ( name = "pages" )
public class Page extends AEntity {

	@NotEmpty
	private String title;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	private String description;


	public String getTitle() {

		return title;
	}


	public void setTitle( final String title ) {

		this.title = title;
	}

	public Page withTitle(final String title){
		this.setTitle( title );
		return this;
	}


	public String getDescription() {

		return description;
	}


	public void setDescription( final String description ) {

		this.description = description;
	}

	public Page withDescription(final String description){
		this.setDescription( description );
		return this;
	}


	@Override
	public String toString() {

		return this.getTitle();
	}

}