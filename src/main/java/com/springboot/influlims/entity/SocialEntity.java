package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "social")
@EntityListeners(AuditingEntityListener.class)
public class SocialEntity extends UserDependentEntity {

	private static final long serialVersionUID = 4233261998468286298L;

	SocialEntity() {}

	public SocialEntity(String network, String externalId, UserEntity userEntity) {
		this.network = network;
		this.externalId = externalId;
		this.userEntity = userEntity;
		this.userId = userEntity.getId();
	}

	@Basic
	@Column(nullable = false)
	private String network;

	@Basic
	@Column(nullable = false)
	private String externalId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_socials_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE"))
	private UserEntity userEntity;

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
