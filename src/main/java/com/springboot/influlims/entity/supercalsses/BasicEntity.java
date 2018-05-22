package com.springboot.influlims.entity.supercalsses;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BasicEntity implements Serializable {

	private static final long serialVersionUID = -8114488962451482231L;

	public BasicEntity() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
	protected Long id;

	@Basic(fetch = FetchType.LAZY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	protected Date createdAt;

	@Basic(fetch = FetchType.LAZY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
	protected Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
