package be.intecbrussel.bbjja.data.dto;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DisablePageRequest implements Serializable {

	private UUID id;
	private Boolean isActive;
	private Boolean isVisible;

}
