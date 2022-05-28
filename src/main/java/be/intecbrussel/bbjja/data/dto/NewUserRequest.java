package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@ToString ( onlyExplicitlyIncluded = true )
@NoArgsConstructor
public class NewUserRequest implements Serializable {

	@Email
	private String email;

	@NotEmpty
	private String username;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotNull
	@Length ( min = 8, max = 256 )
	private String hashedPassword;

}
