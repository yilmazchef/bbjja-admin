package be.intecbrussel.bbjja.data.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true, callSuper = true )
@NoArgsConstructor
@Accessors ( chain = true )
// JPA & HIBERNATE
@Entity
@Table ( name = "pages" )
public class Page extends AEntity {

	@ToString.Include
	@NotEmpty
	private String title;

	@EqualsAndHashCode.Include
	@NotEmpty
	@Column ( unique = true, nullable = false )
	private String slug;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	private String description;

	@ManyToMany
	@JoinTable ( name = "followers" )
	private Set< Subscriber > subscribers = new HashSet<>();

}