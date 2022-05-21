package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@NoArgsConstructor
@Accessors ( chain = true )
public class NewPageRequest implements Serializable {

	@ToString.Include
	@NotEmpty
	private String title;

	@EqualsAndHashCode.Include
	@NotEmpty
	private String slug;

	private String description;


}
