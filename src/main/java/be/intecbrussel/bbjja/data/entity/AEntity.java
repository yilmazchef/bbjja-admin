package be.intecbrussel.bbjja.data.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

// LOMBOK
@Getter
@Setter
@ToString ( onlyExplicitlyIncluded = true )
@EqualsAndHashCode ( onlyExplicitlyIncluded = true )
@Accessors ( chain = true )
// JPA & HIBERNATE
@MappedSuperclass
public abstract class AEntity {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	@Type ( type = "uuid-char" )
	private UUID id;

	private LocalDateTime dateCreated;

	private LocalDateTime dateModified;

	private String createdBy;

	private String modifiedBy;

	private Boolean isActive;


	@PrePersist
	public void prePersist() {

		this.setDateCreated( LocalDateTime.now() );
		this.setIsActive( Boolean.TRUE );
		if ( this.getIsActive() == null ) {
			this.setIsActive( Boolean.TRUE );
		}
	}


	@PreUpdate
	public void preUpdate() {

		if ( this.getDateCreated() != null ) {
			this.setDateModified( LocalDateTime.now() );
		}

		if ( this.getIsActive() == null ) {
			this.setIsActive( Boolean.TRUE );
		}

	}

}
