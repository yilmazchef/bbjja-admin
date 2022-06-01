package be.intecbrussel.bbjja.data.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true, callSuper = true )
@NoArgsConstructor
@Accessors ( chain = true )
// JPA & HIBERNATE
@Entity
@Table ( name = "teams" )
public class Team extends AEntity {


	@EqualsAndHashCode.Include
	@Column ( unique = true, nullable = false )
	@Email
	private String email;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String jobTitle;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String profilePictureUrl;


}
