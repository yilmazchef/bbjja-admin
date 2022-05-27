package be.intecbrussel.bbjja.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSchoolRequest implements Serializable {

	private UUID id;
	private Boolean isActive;
	@NotEmpty
	private String title;
	@NotEmpty
	private String phone;
	@NotNull
	private Float latitude;
	@NotNull
	private Float longitude;
	@Pattern ( regexp = "(?<=src=\").*?(?=[\\?\"])\n", message = "iframe format is invalid" )
	private String iframe;
	private UUID offerId;

}
