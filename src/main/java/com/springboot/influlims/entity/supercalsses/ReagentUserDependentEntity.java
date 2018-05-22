package com.springboot.influlims.entity.supercalsses;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ReagentUserDependentEntity extends UserDependentEntity {

	private static final long serialVersionUID = -2701176594663631751L;

	public ReagentUserDependentEntity() {}

	public ReagentUserDependentEntity(Long userId, Long reagentId) {
		super(userId);
		this.reagentId = reagentId;
	}

	@Basic
	@Column(nullable = false, insertable = false, updatable = false,columnDefinition = "INT(11) UNSIGNED")
	protected Long reagentId;

	public Long getReagentId() {
		return reagentId;
	}

	public void setReagentId(Long reagentId) {
		this.reagentId = reagentId;
	}
}
