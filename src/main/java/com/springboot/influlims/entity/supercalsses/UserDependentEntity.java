package com.springboot.influlims.entity.supercalsses;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UserDependentEntity extends BasicEntity {

	private static final long serialVersionUID = -2332444671400168653L;

	public UserDependentEntity() {}

	public UserDependentEntity(Long userId) {
		this.userId = userId;
	}

	@Basic
	@Column(insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	protected Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
