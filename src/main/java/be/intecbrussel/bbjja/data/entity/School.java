package be.intecbrussel.bbjja.data.entity;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true, callSuper = true )
@NoArgsConstructor
@Accessors ( chain = true )
// JPA & HIBERNATE
@Entity
@Table ( name = "schools" )
public class School extends AEntity {

	@EqualsAndHashCode.Include
	@ToString.Include
	@NotEmpty
	private String title;

	@NotEmpty
	private String phone;

	@EqualsAndHashCode.Include
	@Column ( nullable = false )
	@NotNull
	private Float latitude;

	@EqualsAndHashCode.Include
	@Column ( nullable = false )
	@NotNull
	private Float longitude;

	@ManyToOne
	@JoinColumn ( name = "offer_id" )
	private Offer offer;

}
