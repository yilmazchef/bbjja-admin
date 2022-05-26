package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@ToString ( onlyExplicitlyIncluded = true )
@NoArgsConstructor
public class UpdateOfferRequest implements Serializable {

	@EqualsAndHashCode.Include
	private UUID id;

	private Boolean isActive;

	@NotEmpty
	private String title;

	@NotEmpty
	private String description;

	@ToString.Include
	@EqualsAndHashCode.Include
	@URL
	private String forwardUrl;

	@NotNull
	private UUID pageId;

}
