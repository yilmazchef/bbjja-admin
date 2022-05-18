package be.intecbrussel.bbjja.data.entity;


import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners ( AuditingEntityListener.class)
public abstract class AEntity {

	@Id
	@GeneratedValue
	@Type ( type = "uuid-char" )
	private UUID id;

	@CreatedDate
	private LocalDateTime dateCreated;

	@LastModifiedDate
	private LocalDateTime dateModified;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String modifiedBy;

	private Boolean isActive;

	public UUID getId() {

		return id;
	}


	public void setId( UUID id ) {

		this.id = id;
	}


	public LocalDateTime getDateCreated() {

		return dateCreated;
	}


	public void setDateCreated( final LocalDateTime dateCreated ) {

		this.dateCreated = dateCreated;
	}


	public LocalDateTime getDateModified() {

		return dateModified;
	}


	public void setDateModified( final LocalDateTime dateModified ) {

		this.dateModified = dateModified;
	}


	public String getCreatedBy() {

		return createdBy;
	}


	public void setCreatedBy( final String createdBy ) {

		this.createdBy = createdBy;
	}


	public String getModifiedBy() {

		return modifiedBy;
	}


	public void setModifiedBy( final String modifiedBy ) {

		this.modifiedBy = modifiedBy;
	}


	public Boolean getActive() {

		return isActive;
	}


	public void setActive( final Boolean active ) {

		isActive = active;
	}


	@Override
	public int hashCode() {

		if ( id != null ) {
			return id.hashCode();
		}
		return super.hashCode();
	}


	@Override
	public boolean equals( Object obj ) {

		if ( ! ( obj instanceof AEntity ) ) {
			return false; // null or other class
		}
		AEntity other = ( AEntity ) obj;

		if ( id != null ) {
			return id.equals( other.id );
		}
		return super.equals( other );
	}

}
