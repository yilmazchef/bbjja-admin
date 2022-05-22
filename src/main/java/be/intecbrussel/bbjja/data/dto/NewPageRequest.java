package be.intecbrussel.bbjja.data.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NewPageRequest implements Serializable {

	@NotEmpty
	private String title;
	@NotEmpty
	private String slug;
	private String description;

}
