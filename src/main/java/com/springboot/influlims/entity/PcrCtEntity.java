package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;

@Entity
@Table(name = "pcr_ct")
public class PcrCtEntity extends UserDependentEntity {

	private static final long serialVersionUID = 3374587509196142383L;

	PcrCtEntity() {}

	public PcrCtEntity(UserEntity userEntity, PcrEntity pcrEntity, String channel, Double value) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.pcrId = pcrEntity.getId();
		this.pcrEntity = pcrEntity;
		this.channel = channel;
		this.value = value;
	}

	public PcrCtEntity(UserEntity userEntity, PcrEntity pcrEntity, String channel, Double value, String control) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.pcrId = pcrEntity.getId();
		this.pcrEntity = pcrEntity;
		this.channel = channel;
		this.value = value;
		this.control = control;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_pcr_cts_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long pcrId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pcrId", foreignKey = @ForeignKey(name = "FK_cts_pcr", foreignKeyDefinition = "FOREIGN KEY (pcr_id) REFERENCES pcr(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private PcrEntity pcrEntity;

	@Basic
	@Column(nullable = false, length = 32)
	private String channel;

	@Basic
	@Column(nullable = false)
	private Double value;

	@Basic
	@Column(length = 32)
	private String control;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public Long getPcrId() {
		return pcrId;
	}

	public void setPcrId(Long pcrId) {
		this.pcrId = pcrId;
	}

	public PcrEntity getPcrEntity() {
		return pcrEntity;
	}

	public void setPcrEntity(PcrEntity pcrEntity) {
		this.pcrEntity = pcrEntity;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}
}
