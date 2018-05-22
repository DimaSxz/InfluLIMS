package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.ReagentUserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "extraction")
public class ExtractionEntity extends ReagentUserDependentEntity {

	private static final long serialVersionUID = 2210820307768908151L;

	ExtractionEntity() {}

	public ExtractionEntity(UserEntity userEntity, ReagentEntity reagentEntity, SampleEntity sampleEntity, String extractionName) {
		super(userEntity.getId(), reagentEntity.getId());
		this.userEntity = userEntity;
		this.reagentEntity = reagentEntity;
		this.sampleId = sampleEntity.getId();
		this.sampleEntity = sampleEntity;
		this.extractionName = extractionName;
	}

	public ExtractionEntity(UserEntity userEntity, ReagentEntity reagentEntity, SampleEntity sampleEntity, String extractionName, Integer volumeIn, Integer volumeOut, String extractionProtocol, Date extractionDate) {
		super(userEntity.getId(), reagentEntity.getId());
		this.userEntity = userEntity;
		this.reagentEntity = reagentEntity;
		this.sampleId = sampleEntity.getId();
		this.sampleEntity = sampleEntity;
		this.extractionName = extractionName;
		this.volumeIn = volumeIn;
		this.volumeOut = volumeOut;
		this.extractionProtocol = extractionProtocol;
		this.extractionDate = extractionDate;
	}

	@OneToMany(mappedBy = "extractionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PcrEntity> pcrEntities = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_extractions_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reagentId", foreignKey = @ForeignKey(name = "FK_extractions_reagent", foreignKeyDefinition = "FOREIGN KEY (reagent_id) REFERENCES reagent(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private ReagentEntity reagentEntity;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long sampleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sampleId", foreignKey = @ForeignKey(name = "FK_extractions_sample", foreignKeyDefinition = "FOREIGN KEY (sample_id) REFERENCES sample(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private SampleEntity sampleEntity;

	@Basic
	@Column(nullable = false)
	private String extractionName;

	@Basic
	@Column(columnDefinition = "SMALLINT(6) UNSIGNED")
	private Integer volumeIn;

	@Basic
	@Column(columnDefinition = "SMALLINT(6) UNSIGNED")
	private Integer volumeOut;

	@Basic
	@Column
	private String extractionProtocol;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date extractionDate;

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

	public Long getSampleId() {
		return sampleId;
	}

	public void setSampleId(Long sampleId) {
		this.sampleId = sampleId;
	}

	public SampleEntity getSampleEntity() {
		return sampleEntity;
	}

	public void setSampleEntity(SampleEntity sampleEntity) {
		this.sampleEntity = sampleEntity;
	}

	public String getExtractionName() {
		return extractionName;
	}

	public void setExtractionName(String extractionName) {
		this.extractionName = extractionName;
	}

	public Integer getVolumeIn() {
		return volumeIn;
	}

	public void setVolumeIn(Integer volumeIn) {
		this.volumeIn = volumeIn;
	}

	public Integer getVolumeOut() {
		return volumeOut;
	}

	public void setVolumeOut(Integer volumeOut) {
		this.volumeOut = volumeOut;
	}

	public String getExtractionProtocol() {
		return extractionProtocol;
	}

	public void setExtractionProtocol(String extractionProtocol) {
		this.extractionProtocol = extractionProtocol;
	}

	public Date getExtractionDate() {
		return extractionDate;
	}

	public void setExtractionDate(Date extractionDate) {
		this.extractionDate = extractionDate;
	}

	public Collection<PcrEntity> getPcrEntities() {
		return pcrEntities;
	}

	public void setPcrEntities(Collection<PcrEntity> pcrEntities) {
		this.pcrEntities = pcrEntities;
	}
}