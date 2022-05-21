package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@NoArgsConstructor
@Accessors ( chain = true )
public class UpdateSlideRequest implements Serializable {

	@NotNull
	private UUID id;

	@NotEmpty
	private String title;

	@EqualsAndHashCode.Include
	@ToString.Include
	@URL
	private String imageUrl;

	@NotNull
	private UUID pageId;

	private Boolean isActive;


}
