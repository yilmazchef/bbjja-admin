package be.intecbrussel.bbjja.data.entity;


import be.intecbrussel.bbjja.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import java.util.Random;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table ( name = "users" )
public class User extends AEntity {

	@NotEmpty
	private String username;
	@NotEmpty
	private String name;
	@JsonIgnore
	private String hashedPassword;
	@Enumerated ( EnumType.STRING )
	@ElementCollection ( fetch = FetchType.EAGER )
	private Set< Role > roles;
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String profilePictureUrl;


	public String getUsername() {

		return username;
	}


	public void setUsername( String username ) {

		this.username = username;
	}


	public User withUsername( String username ) {

		this.setUsername( username );
		return this;
	}


	public String getName() {

		return name;
	}


	public void setName( String name ) {

		this.name = name;
	}


	public User withName( String name ) {

		this.setName( name );
		return this;
	}


	public String getHashedPassword() {

		return hashedPassword;
	}


	public void setHashedPassword( String hashedPassword ) {

		this.hashedPassword = hashedPassword;
	}


	public User withHashedPassword( String hashedPassword ) {

		this.setHashedPassword( hashedPassword );
		return this;
	}


	public User withHashedRandomPassword() {

		this.setHashedPassword( String.valueOf( new Random().nextInt( 999999 ) + 100000 ) );
		return this;
	}


	public Set< Role > getRoles() {

		return roles;
	}


	public void setRoles( Set< Role > roles ) {

		this.roles = roles;
	}


	public User withRole( Role role ) {

		this.getRoles().add( role );
		return this;
	}


	public User withoutRole( Role role ) {

		this.getRoles().remove( role );
		return this;
	}


	public String getProfilePictureUrl() {

		return profilePictureUrl;
	}


	public void setProfilePictureUrl( String profilePictureUrl ) {

		this.profilePictureUrl = profilePictureUrl;
	}


	public User withProfilePictureUrl( String profilePictureUrl ) {

		this.setProfilePictureUrl( profilePictureUrl );
		return this;
	}

}
