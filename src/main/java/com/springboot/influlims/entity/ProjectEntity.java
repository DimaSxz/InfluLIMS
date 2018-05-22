package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "project")
public class ProjectEntity extends UserDependentEntity {

	private static final long serialVersionUID = -6798287871869830309L;

	public ProjectEntity() {}

	public ProjectEntity(String projectName, UserEntity userEntity) {
		super(userEntity.getId());
		this.projectName = projectName;
		this.userEntity = userEntity;
	}

	public ProjectEntity(String projectName, String projectNotes, UserEntity userEntity) {
		super(userEntity.getId());
		this.projectName = projectName;
		this.projectNotes = projectNotes;
		this.userEntity = userEntity;
	}

	@OneToMany(mappedBy = "projectEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProviderProjectEntity> providerProjectEntities = new ArrayList<>();

	@Basic
	@Column(nullable = false)
	private String projectName;

	@Basic
	@Column(columnDefinition = "LONGTEXT", length = -1)
	private String projectNotes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_projects_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNotes() {
		return projectNotes;
	}

	public void setProjectNotes(String projectNotes) {
		this.projectNotes = projectNotes;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public Collection<ProviderProjectEntity> getProviderProjectEntities() {
		return providerProjectEntities;
	}

	public void setProviderProjectEntities(Collection<ProviderProjectEntity> providerProjectEntities) {
		this.providerProjectEntities = providerProjectEntities;
	}
}
