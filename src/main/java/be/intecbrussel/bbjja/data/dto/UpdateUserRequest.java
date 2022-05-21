package be.intecbrussel.bbjja.data.dto;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@NoArgsConstructor
@Accessors ( chain = true )
public class UpdateUserRequest implements Serializable {

	private UUID id;

	@ToString.Include
	@EqualsAndHashCode.Include
	@Email
	private String email;

	@ToString.Include
	@EqualsAndHashCode.Include
	@NotEmpty
	private String username;

	private String firstName;

	private String lastName;

	@NotEmpty
	private String hashedPassword;

	@NotEmpty
	@URL
	private String profilePictureUrl;

}
