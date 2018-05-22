package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRoleEntity extends UserDependentEntity {

	private static final long serialVersionUID = 6384004072293136925L;

	UserRoleEntity() {}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_role_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE"))
	private UserEntity userEntity;

	public UserRoleEntity(UserEntity userEntity, RoleEntity roleEntity) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.roleId = roleEntity.getId();
		this.roleEntity = roleEntity;
	}

	@Basic
	@Column(insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long roleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "FK_user_role", foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private RoleEntity roleEntity;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}
}
