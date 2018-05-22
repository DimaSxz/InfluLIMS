package com.springboot.influlims.entity;

import com.springboot.influlims.entity.enums.Role;
import com.springboot.influlims.entity.supercalsses.BasicEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(name = "UK_roleName", columnNames = {"name"}))
public class RoleEntity extends BasicEntity {

	private static final long serialVersionUID = -7004724102974900207L;

	RoleEntity() {}

	public RoleEntity(Role name) {
		this.name = name;
	}

	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role name;

	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<UserRoleEntity> userRolesEntities = new ArrayList<>();

//
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "role_user",
//			joinColumns = @JoinColumn(
//					name = "role_id",
//					nullable = false,
//					foreignKey = @ForeignKey(
//							name = "FK_roles_user",
//							foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE RESTRICT ON UPDATE CASCADE"
//					)
//			),
//			inverseJoinColumns = @JoinColumn(
//					name = "user_id",
//					nullable = false,
//					foreignKey = @ForeignKey(
//							name = "FK_users_role",
//							foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"
//					)
//			)
//	)
//	public Collection<UserEntity> userEntities = new ArrayList<>();

	public Role getName() {
		return name;
	}

	public void setName(Role name) {
		this.name = name;
	}

	public Collection<UserRoleEntity> getUserRolesEntities() {
		return userRolesEntities;
	}

	public void setUserRolesEntities(Collection<UserRoleEntity> userRolesEntities) {
		this.userRolesEntities = userRolesEntities;
	}

	//	public Collection<UserEntity> getUserEntities() {
//		return userEntities;
//	}
//
//	public void setUserEntities(Collection<UserEntity> userEntities) {
//		this.userEntities = userEntities;
//	}

}
