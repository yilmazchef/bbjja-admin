package be.intecbrussel.bbjja.data.dto;


import be.intecbrussel.bbjja.data.entity.Grappling;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrapplingResponse implements Serializable {

	private UUID id;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	private String createdBy;
	private String modifiedBy;
	private Boolean isActive;
	private Grappling.GrapplingType type;
	@NotEmpty
	private String introduction;
	@NotEmpty
	private String forWho;
	@NotEmpty
	private String practice;
	@URL
	private String videoUrl;
	private UUID pageId;

}
