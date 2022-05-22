package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NewOfferRequest implements Serializable {

	@NotEmpty
	private String title;
	@NotEmpty
	private String description;
	@URL
	private String forwardUrl;

	private UUID pageId;

}
