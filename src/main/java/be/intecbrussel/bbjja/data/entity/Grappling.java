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
@Table ( name = "grapplings" )
public class Grappling extends AEntity {

	public enum GrapplingType {
		SCHOOL,
		STREET
	}

	@Enumerated ( EnumType.STRING )
	@Column ( name = "type", nullable = false )
	private GrapplingType type;

	@ToString.Include
	@NotEmpty
	@Column ( nullable = false )
	private String introduction;

	@NotEmpty
	@Column ( nullable = false )
	private String forWho;

	@NotEmpty
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	private String practice;

	@EqualsAndHashCode.Include
	@Column ( nullable = false, unique = true )
	@Lob
	@Type ( type = "org.hibernate.type.TextType" )
	@URL
	private String videoUrl;

	@ManyToOne
	@JoinColumn ( name = "page_id" )
	private Page page;


}
