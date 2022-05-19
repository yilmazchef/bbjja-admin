package be.intecbrussel.bbjja.data.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table ( name = "pages" )
public class Page extends AEntity {

	@NotEmpty
	private String title;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	private String description;

	@ManyToMany
	@JoinTable ( name = "followers" )
	private Set< Subscriber > subscribers = new LinkedHashSet<>();


	public void addSubscriber( final Subscriber subscriber ) {

		this.getSubscribers().add( subscriber );
	}


	public Page withSubscriber( final Subscriber subscriber ) {

		this.addSubscriber( subscriber );
		return this;
	}


	public void removeSubscriber( final Subscriber subscriber ) {

		this.getSubscribers().remove( subscriber );
	}


	public Page withoutSubscriber( final Subscriber subscriber ) {

		this.removeSubscriber( subscriber );
		return this;
	}


	public Set< Subscriber > getSubscribers() {

		return subscribers;
	}


	public String getTitle() {

		return title;
	}


	public void setTitle( final String title ) {

		this.title = title;
	}


	public Page withTitle( final String title ) {

		this.setTitle( title );
		return this;
	}


	public String getDescription() {

		return description;
	}


	public void setDescription( final String description ) {

		this.description = description;
	}


	public Page withDescription( final String description ) {

		this.setDescription( description );
		return this;
	}


	@Override
	public String toString() {

		return this.getTitle();
	}

}