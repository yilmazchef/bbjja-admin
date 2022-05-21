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
public class NewUserRequest implements Serializable {

	@EqualsAndHashCode.Include
	@ToString.Include
	@Email
	private String email;

	@EqualsAndHashCode.Include
	@ToString.Include
	@NotEmpty
	private String username;

	@NotEmpty
	private String hashedPassword;

	private String firstName;

	private String lastName;

}
