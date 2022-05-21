package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@NoArgsConstructor
@Accessors ( chain = true )
public class NewSubscriberRequest implements Serializable {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@EqualsAndHashCode.Include
	@ToString.Include
	@Email
	private String email;


}
