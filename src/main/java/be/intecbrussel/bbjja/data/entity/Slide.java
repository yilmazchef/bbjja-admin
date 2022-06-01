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
@Table ( name = "slides" )
public class Slide extends AEntity {

	@NotEmpty
	private String title;

	@EqualsAndHashCode.Include
	@Column ( nullable = false, unique = true )
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String imageUrl;

	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@Column
	private String description;

	@ManyToOne
	@JoinColumn ( name = "page_id" )
	private Page page;


}