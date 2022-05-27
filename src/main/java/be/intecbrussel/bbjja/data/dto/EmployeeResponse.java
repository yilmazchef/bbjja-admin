package be.intecbrussel.bbjja.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse implements Serializable {

	private UUID id;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	private String createdBy;
	private String modifiedBy;
	private Boolean isActive;
	@Email
	private String email;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String jobTitle;
	@URL
	private String profilePictureUrl;

}
