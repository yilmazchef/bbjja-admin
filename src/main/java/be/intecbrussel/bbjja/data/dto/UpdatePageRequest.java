package be.intecbrussel.bbjja.data.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UpdatePageRequest implements Serializable {

	private UUID id;
	@NotEmpty
	private String title;
	@NotEmpty
	private String slug;
	private String description;
	private Boolean isVisible;

}
