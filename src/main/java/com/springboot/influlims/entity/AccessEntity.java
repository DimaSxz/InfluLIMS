package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.BasicEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "access")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener.class)
public class AccessEntity extends BasicEntity {

	private static final long serialVersionUID = -8042460965049155608L;

	AccessEntity() {}

	public AccessEntity(String rights) {
		this.rights = rights;
	}

	public AccessEntity(String rights, String description) {
		this.rights = rights;
		this.description = description;
	}
//
//	@OneToMany(mappedBy = "accessEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	private Collection<UserEntity> userEntities = new ArrayList<>();

	@Basic
	@Column(nullable = false, columnDefinition = "LONGTEXT", length = -1)
	private String rights;

	@Basic
	@Column(columnDefinition = "LONGTEXT", length = -1)
	private String description;

//	public Collection<UserEntity> getUserEntities() {
//		return userEntities;
//	}
//
//	public void setUserEntities(Collection<UserEntity> userEntities) {
//		this.userEntities = userEntities;
//	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
