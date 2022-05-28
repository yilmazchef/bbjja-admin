package be.intecbrussel.bbjja.data.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true, callSuper = true )
@NoArgsConstructor
@Accessors ( chain = true )
// JPA & HIBERNATE
@Entity
@Table ( name = "roles" )
public class Role extends AEntity {

	@Transient
	public static final Role ADMIN = new Role()
			.setTitle( "ADMIN" )
			.setDescription( "Has FULL access to all UI pages and endpoints." )
			.setMaxAllowedUsers( 10 );

	@Transient
	public static final Role EDITOR = new Role()
			.setTitle( "EDITOR" )
			.setDescription( "Has access to all UI pages." )
			.setMaxAllowedUsers( 1000 );

	@Transient
	public static final Role USER = new Role()
			.setTitle( "USER" )
			.setDescription( "Has LIMITED access to all pages." )
			.setMaxAllowedUsers( 1000000 );


	@EqualsAndHashCode.Include
	@Column ( unique = true, nullable = false )
	@NotEmpty
	private String title;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	private String description;

	@PositiveOrZero
	private Integer maxAllowedUsers;

	@OneToOne
	@JoinColumn ( name = "parent_id" )
	private Role parent;

}
