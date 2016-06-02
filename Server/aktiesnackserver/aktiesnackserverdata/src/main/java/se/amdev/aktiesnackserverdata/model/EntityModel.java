package se.amdev.aktiesnackserverdata.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class EntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "creation_time")
	@CreatedDate
	private Date creationTime;

	@Column(name = "last_updated_time")
	@LastModifiedDate
	private Date lastUpdatedTime;

	public Long getId() {
		return id;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
}