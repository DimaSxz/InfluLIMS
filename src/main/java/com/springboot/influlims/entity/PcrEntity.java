package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.ReagentUserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "pcr")
public class PcrEntity extends ReagentUserDependentEntity {

	private static final long serialVersionUID = 7103776312716674020L;

	PcrEntity() {}

	public PcrEntity(UserEntity userEntity, ReagentEntity reagentEntity, ExtractionEntity extractionEntity, String pcrName) {
		super(userEntity.getId(), reagentEntity.getId());
		this.userEntity = userEntity;
		this.reagentEntity = reagentEntity;
		this.extractionId = extractionEntity.getId();
		this.extractionEntity = extractionEntity;
		this.pcrName = pcrName;
	}

	public PcrEntity(UserEntity userEntity, ReagentEntity reagentEntity, ExtractionEntity extractionEntity, String pcrName, String pcrProtocol, String pcrReport, Date pcrDate) {
		super(userEntity.getId(), reagentEntity.getId());
		this.userEntity = userEntity;
		this.reagentEntity = reagentEntity;
		this.extractionId = extractionEntity.getId();
		this.extractionEntity = extractionEntity;
		this.pcrName = pcrName;
		this.pcrProtocol = pcrProtocol;
		this.pcrReport = pcrReport;
		this.pcrDate = pcrDate;
	}

	@OneToMany(mappedBy = "pcrEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PcrCtEntity> pcrCtEntities = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_pcrs_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reagentId", foreignKey = @ForeignKey(name = "FK_pcrs_reagent", foreignKeyDefinition = "FOREIGN KEY (reagent_id) REFERENCES reagent(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private ReagentEntity reagentEntity;

	@Basic
	@Column(nullable = false, insertable = false, updatable =  false, columnDefinition = "INT(11) UNSIGNED")
	private Long extractionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "extractionId", foreignKey = @ForeignKey(name = "FK_pcrs_extraction", foreignKeyDefinition = "FOREIGN KEY (extraction_id) REFERENCES extraction(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private ExtractionEntity extractionEntity;

	@Basic
	@Column(nullable = false)
	private String pcrName;

	@Basic
	@Column
	private String pcrProtocol;

	@Basic
	@Column
	private String pcrReport;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date pcrDate;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public ReagentEntity getReagentEntity() {
		return reagentEntity;
	}

	public void setReagentEntity(ReagentEntity reagentEntity) {
		this.reagentEntity = reagentEntity;
	}

	public Long getExtractionId() {
		return extractionId;
	}

	public void setExtractionId(Long extractionId) {
		this.extractionId = extractionId;
	}

	public ExtractionEntity getExtractionEntity() {
		return extractionEntity;
	}

	public void setExtractionEntity(ExtractionEntity extractionEntity) {
		this.extractionEntity = extractionEntity;
	}

	public String getPcrName() {
		return pcrName;
	}

	public void setPcrName(String pcrName) {
		this.pcrName = pcrName;
	}

	public String getPcrProtocol() {
		return pcrProtocol;
	}

	public void setPcrProtocol(String pcrProtocol) {
		this.pcrProtocol = pcrProtocol;
	}

	public String getPcrReport() {
		return pcrReport;
	}

	public void setPcrReport(String pcrReport) {
		this.pcrReport = pcrReport;
	}

	public Date getPcrDate() {
		return pcrDate;
	}

	public void setPcrDate(Date pcrDate) {
		this.pcrDate = pcrDate;
	}

	public Collection<PcrCtEntity> getPcrCtEntities() {
		return pcrCtEntities;
	}

	public void setPcrCtEntities(Collection<PcrCtEntity> pcrCtEntities) {
		this.pcrCtEntities = pcrCtEntities;
	}
}
