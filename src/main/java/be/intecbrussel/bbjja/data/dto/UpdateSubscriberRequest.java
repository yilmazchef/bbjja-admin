package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
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
public class UpdateSubscriberRequest implements Serializable {

	@NotNull
	private UUID id;

	private String firstName;

	private String lastName;

	@EqualsAndHashCode.Include
	@ToString.Include
	@NotEmpty
	@Email
	private String email;

	private Boolean isActive;

}
