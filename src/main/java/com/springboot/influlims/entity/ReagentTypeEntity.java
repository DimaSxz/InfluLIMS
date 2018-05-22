package com.springboot.influlims.entity;

import com.springboot.influlims.entity.enums.ReagentFunction;
import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "reagent_type")
public class ReagentTypeEntity extends UserDependentEntity {

	private static final long serialVersionUID = -2595685136709594082L;

	ReagentTypeEntity() {}

	public ReagentTypeEntity(String reagentName, UserEntity userEntity) {
		super(userEntity.getId());
		this.function = ReagentFunction.EXT;
		this.reagentName = reagentName;
		this.userEntity = userEntity;
	}

	public ReagentTypeEntity(String reagentName, String pcrTestChannels, String detectionChannels, UserEntity userEntity) {
		super(userEntity.getId());
		this.function = ReagentFunction.PCR;
		this.reagentName = reagentName;
		this.pcrTestChannels = pcrTestChannels;
		this.detectionChannels = detectionChannels;
		this.userEntity = userEntity;
	}

	@OneToMany(mappedBy = "reagentTypeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ReagentEntity> reagentEntities = new ArrayList<>();

	@Basic
	@Column(nullable = false)
	private String reagentName;

	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 3)
	private ReagentFunction function;

	@Basic
	@Column
	private String pcrTestChannels;

	@Basic
	@Column
	private String detectionChannels;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_reagent_type_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}

	public ReagentFunction getFunction() {
		return function;
	}

	public void setFunction(ReagentFunction function) {
		this.function = function;
	}

	public String getPcrTestChannels() {
		return pcrTestChannels;
	}

	public void setPcrTestChannels(String pcrTestChannels) {
		this.pcrTestChannels = pcrTestChannels;
	}

	public String getDetectionChannels() {
		return detectionChannels;
	}

	public void setDetectionChannels(String detectionChannels) {
		this.detectionChannels = detectionChannels;
	}

	public Collection<ReagentEntity> getReagentEntities() {
		return reagentEntities;
	}

	public void setReagentEntities(Collection<ReagentEntity> reagentEntities) {
		this.reagentEntities = reagentEntities;
	}
}
