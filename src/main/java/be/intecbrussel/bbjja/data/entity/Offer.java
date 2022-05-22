package be.intecbrussel.bbjja.data.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
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
@Table ( name = "offers" )
public class Offer extends AEntity {


	@ToString.Include
	@NotEmpty
	@Column ( nullable = false )
	private String title;

	@NotEmpty
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	private String description;

	@EqualsAndHashCode.Include
	@Column ( nullable = false, unique = true )
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String forwardUrl;

	@ManyToOne
	@JoinColumn ( name = "page_id" )
	private Page page;

}
