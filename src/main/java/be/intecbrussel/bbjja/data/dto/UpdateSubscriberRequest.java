package be.intecbrussel.bbjja.data.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UpdateSubscriberRequest implements Serializable {

	private UUID id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Email
	private String email;

}
