package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.BasicEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "UK_login", columnNames = {"login"}))
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends BasicEntity {

	private static final long serialVersionUID = 1787700067119778180L;

	public UserEntity() {}

//	public UserEntity(AccessEntity accessEntity, String login, String password) {
	public UserEntity(String login, String password) {
//		this.accessEntity = accessEntity;
//		this.accessId = accessEntity.getId();
		this.login = login;
		this.password = password;
	}
	public UserEntity(String login, String password, Boolean isTest) {
		this.login = login;
		this.password = password;
		this.isTest = isTest;
	}

//	public UserEntity(AccessEntity accessEntity, String login, String password, String fullName, String email, String phone, String position) {
	public UserEntity(String login, String password, String fullName, String email, String phone, String position) {
//		this.accessEntity = accessEntity;
//		this.accessId = accessEntity.getId();
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.position = position;
	}

	public UserEntity(String login, String password, String fullName, String email, String phone, String position, Boolean isTest) {
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.position = position;
		this.isTest = isTest;
	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "role_user",
//			joinColumns = @JoinColumn(
//					name = "user_id",
//					nullable = false,
//					foreignKey = @ForeignKey(
//							name = "FK_users_role",
//							foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"
//					)
//			),
//			inverseJoinColumns = @JoinColumn(
//					name = "role_id",
//					nullable = false,
//					foreignKey = @ForeignKey(
//							name = "FK_roles_user",
//							foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE RESTRICT ON UPDATE CASCADE"
//					)
//			)
//	)
//	public Collection<RoleEntity> roleEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<SocialEntity> socialEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProviderEntity> providerEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PatientEntity> patientEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProjectEntity> ProjectEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProviderProjectEntity> providerProjectEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<SampleEntity> sampleEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ReagentTypeEntity> reagentTypeEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ReagentEntity> reagentEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ExtractionEntity> extractionEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PcrEntity> pcrEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PcrCtEntity> pcrCtEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<SocialEntity> socialAccountEntities = new ArrayList<>(0);

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<UserRoleEntity> userRolesEntities = new ArrayList<>();

//	@Basic
//	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
//	private Long accessId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable = false, name = "accessId", foreignKey = @ForeignKey(name = "FK_users_access", foreignKeyDefinition = "FOREIGN KEY (access_id) REFERENCES access(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
//	private AccessEntity accessEntity;

//	public AccessEntity getAccessEntity() {
//		return accessEntity;
//	}
//
//	public void setAccessEntity(AccessEntity accessEntity) {
//		this.accessEntity = accessEntity;
//	}

	@Basic
	@Column(nullable = false, length = 128)
	private String login;

	@Basic
	@Column(nullable = false)
	private String password;

	@Basic
	@Column
	private String fullName;

	@Basic
	@Column
	private String email;

	@Basic
	@Column(length = 32)
	private String phone;

	@Basic
	@Column
	private String position;

	@Basic
	@Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean isTest = false;

//	public Long getAccessId() {
//		return accessId;
//	}
//
//	public void setAccessId(Long accessId) {
//		this.accessId = accessId;
//	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Collection<SocialEntity> getSocialEntities() {
		return socialEntities;
	}

	public void setSocialEntities(Collection<SocialEntity> socialEntities) {
		this.socialEntities = socialEntities;
	}

	public Collection<ProviderEntity> getProviderEntities() {
		return providerEntities;
	}

	public void setProviderEntities(Collection<ProviderEntity> providerEntities) {
		this.providerEntities = providerEntities;
	}

	public Collection<PatientEntity> getPatientEntities() {
		return patientEntities;
	}

	public void setPatientEntities(Collection<PatientEntity> patientEntities) {
		this.patientEntities = patientEntities;
	}

	public Collection<ProjectEntity> getProjectEntities() {
		return ProjectEntities;
	}

	public void setProjectEntities(Collection<ProjectEntity> projectEntities) {
		ProjectEntities = projectEntities;
	}

	public Collection<ProviderProjectEntity> getProviderProjectEntities() {
		return providerProjectEntities;
	}

	public void setProviderProjectEntities(Collection<ProviderProjectEntity> providerProjectEntities) {
		this.providerProjectEntities = providerProjectEntities;
	}

	public Collection<SampleEntity> getSampleEntities() {
		return sampleEntities;
	}

	public void setSampleEntities(Collection<SampleEntity> sampleEntities) {
		this.sampleEntities = sampleEntities;
	}

	public Collection<ReagentTypeEntity> getReagentTypeEntities() {
		return reagentTypeEntities;
	}

	public void setReagentTypeEntities(Collection<ReagentTypeEntity> reagentTypeEntities) {
		this.reagentTypeEntities = reagentTypeEntities;
	}

	public Collection<ReagentEntity> getReagentEntities() {
		return reagentEntities;
	}

	public void setReagentEntities(Collection<ReagentEntity> reagentEntities) {
		this.reagentEntities = reagentEntities;
	}

	public Collection<ExtractionEntity> getExtractionEntities() {
		return extractionEntities;
	}

	public void setExtractionEntities(Collection<ExtractionEntity> extractionEntities) {
		this.extractionEntities = extractionEntities;
	}

	public Collection<PcrEntity> getPcrEntities() {
		return pcrEntities;
	}

	public void setPcrEntities(Collection<PcrEntity> pcrEntities) {
		this.pcrEntities = pcrEntities;
	}

	public Collection<PcrCtEntity> getPcrCtEntities() {
		return pcrCtEntities;
	}

	public void setPcrCtEntities(Collection<PcrCtEntity> pcrCtEntities) {
		this.pcrCtEntities = pcrCtEntities;
	}

	public Collection<SocialEntity> getSocialAccountEntities() {
		return socialAccountEntities;
	}

	public void setSocialAccountEntities(Collection<SocialEntity> socialAccountEntities) {
		this.socialAccountEntities = socialAccountEntities;
	}

	public Collection<UserRoleEntity> getUserRolesEntities() {
		return userRolesEntities;
	}

	public void setUserRolesEntities(Collection<UserRoleEntity> userRolesEntities) {
		this.userRolesEntities = userRolesEntities;
	}

	//	public Collection<RoleEntity> getRoleEntities() {
//		return roleEntities;
//	}
//
//	public void setRoleEntities(Collection<RoleEntity> roleEntities) {
//		this.roleEntities = roleEntities;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getTest() {
		return isTest;
	}

	public void setTest(Boolean test) {
		isTest = test;
	}
}
