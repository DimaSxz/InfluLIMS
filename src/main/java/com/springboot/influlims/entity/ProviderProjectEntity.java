package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "provider_project")
public class ProviderProjectEntity extends UserDependentEntity {

	private static final long serialVersionUID = -7502401968373877508L;

	ProviderProjectEntity() {}

	public ProviderProjectEntity(UserEntity userEntity, ProviderEntity providerEntity, ProjectEntity projectEntity) {
		super(userEntity.getId());
		this.providerId = providerEntity.getId();
		this.projectId = projectEntity.getId();
		this.userEntity = userEntity;
		this.providerEntity = providerEntity;
		this.projectEntity = projectEntity;
	}

	@OneToMany(mappedBy = "providerProjectEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<SampleEntity> sampleEntities = new ArrayList<>();

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long providerId;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long projectId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_provider_project_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "providerId", foreignKey = @ForeignKey(name = "FK_providers_project", foreignKeyDefinition = "FOREIGN KEY (provider_id) REFERENCES provider(id) ON DELETE CASCADE ON UPDATE CASCADE"))
	private ProviderEntity providerEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId", foreignKey = @ForeignKey(name = "FK_projects_provider", foreignKeyDefinition = "FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE ON UPDATE CASCADE"))
	private ProjectEntity projectEntity;

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public ProviderEntity getProviderEntity() {
		return providerEntity;
	}

	public void setProviderEntity(ProviderEntity providerEntity) {
		this.providerEntity = providerEntity;
	}

	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	public Collection<SampleEntity> getSampleEntities() {
		return sampleEntities;
	}

	public void setSampleEntities(Collection<SampleEntity> sampleEntities) {
		this.sampleEntities = sampleEntities;
	}
}
