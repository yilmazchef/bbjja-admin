package be.intecbrussel.bbjja.data.entity;


import be.intecbrussel.bbjja.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true, callSuper = true )
@NoArgsConstructor
@Accessors (chain = true )
// JPA & HIBERNATE
@Entity
@Table ( name = "users" )
public class User extends AEntity {


	@EqualsAndHashCode.Include
	@Column ( unique = true, nullable = false )
	@Email
	private String email;

	@EqualsAndHashCode.Include
	@Column ( unique = true, nullable = false )
	@NotEmpty
	private String username;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@JsonIgnore
	private String hashedPassword;

	@Enumerated ( EnumType.STRING )
	@ElementCollection ( fetch = FetchType.EAGER )
	private Set< Role > roles;
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String profilePictureUrl;


}
