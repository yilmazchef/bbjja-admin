package be.intecbrussel.bbjja.data.entity;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true, callSuper = true )
@NoArgsConstructor
@Accessors (chain = true )
// JPA & HIBERNATE
@Entity
@Table ( name = "subscribers" )
public class Subscriber extends AEntity {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@EqualsAndHashCode.Include
	@Column ( nullable = false, unique = true )
	@Email
	private String email;


}