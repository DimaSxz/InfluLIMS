package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "reagent")
public class ReagentEntity extends UserDependentEntity {

	private static final long serialVersionUID = -4288947915279817959L;

	public ReagentEntity() {}

	public ReagentEntity(UserEntity userEntity, ReagentTypeEntity reagentTypeEntity, String kitLot, Long maxResource) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.reagentTypeId = reagentTypeEntity.getId();
		this.reagentTypeEntity = reagentTypeEntity;
		this.kitLot = kitLot;
		this.maxResource = maxResource;
	}

	public ReagentEntity(UserEntity userEntity, ReagentTypeEntity reagentTypeEntity, String kitLot, Long maxResource, Long remainResource) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.reagentTypeId = reagentTypeEntity.getId();
		this.reagentTypeEntity = reagentTypeEntity;
		this.kitLot = kitLot;
		this.maxResource = maxResource;
		this.remainResource = remainResource;
	}

	@OneToMany(mappedBy = "reagentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ExtractionEntity> extractionEntities = new ArrayList<>();

	@OneToMany(mappedBy = "reagentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PcrEntity> pcrEntities = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_reagents_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long reagentTypeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reagentTypeId", foreignKey = @ForeignKey(name = "FK_reagents_reagent_type", foreignKeyDefinition = "FOREIGN KEY (reagent_type_id) REFERENCES reagent_type(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private ReagentTypeEntity reagentTypeEntity;

	@Basic
	@Column(nullable = false)
	private String kitLot;

	@Basic
	@Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long maxResource;

	@Basic
	@Column(columnDefinition = "INT(11) UNSIGNED")
	private Long remainResource;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public Long getReagentTypeId() {
		return reagentTypeId;
	}

	public void setReagentTypeId(Long reagentTypeId) {
		this.reagentTypeId = reagentTypeId;
	}

	public ReagentTypeEntity getReagentTypeEntity() {
		return reagentTypeEntity;
	}

	public void setReagentTypeEntity(ReagentTypeEntity reagentTypeEntity) {
		this.reagentTypeEntity = reagentTypeEntity;
	}

	public String getKitLot() {
		return kitLot;
	}

	public void setKitLot(String kitLot) {
		this.kitLot = kitLot;
	}

	public Long getMaxResource() {
		return maxResource;
	}

	public void setMaxResource(Long maxResource) {
		this.maxResource = maxResource;
	}

	public Long getRemainResource() {
		return remainResource;
	}

	public void setRemainResource(Long remainResource) {
		this.remainResource = remainResource;
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
}
