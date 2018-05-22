package com.springboot.influlims.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "container", uniqueConstraints = @UniqueConstraint(name = "UK_sample_id", columnNames = {"sampleId"}))
public class ContainerEntity implements Serializable {

	private static final long serialVersionUID = 166576367941217118L;

	ContainerEntity() {}

	public ContainerEntity(SampleEntity sampleEntity) {
		this.sampleId = sampleEntity.getId();
		this.sampleEntity = sampleEntity;
	}

	public ContainerEntity(SampleEntity sampleEntity, Boolean isPropered, Boolean isMarked, Boolean markDirectionEquals, Boolean contamination, Boolean isEnough) {
		this.sampleId = sampleEntity.getId();
		this.sampleEntity = sampleEntity;
		this.isPropered = isPropered;
		this.isMarked = isMarked;
		this.markDirectionEquals = markDirectionEquals;
		this.contamination = contamination;
		this.isEnough = isEnough;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long id;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long sampleId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sampleId", foreignKey = @ForeignKey(name = "FK_sample_container", foreignKeyDefinition = "FOREIGN KEY (sample_id) REFERENCES sample(id) ON DELETE CASCADE ON UPDATE CASCADE"))
	private SampleEntity sampleEntity;

	@Basic
	@Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
	private Boolean isPropered;

	@Basic
	@Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
	private Boolean isMarked;

	@Basic
	@Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
	private Boolean markDirectionEquals;

	@Basic
	@Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean contamination;

	@Basic
	@Column(nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
	private Boolean isEnough;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getPropered() {
		return isPropered;
	}

	public void setPropered(Boolean propered) {
		isPropered = propered;
	}

	public Boolean getMarked() {
		return isMarked;
	}

	public void setMarked(Boolean marked) {
		isMarked = marked;
	}

	public Boolean getMarkDirectionEquals() {
		return markDirectionEquals;
	}

	public void setMarkDirectionEquals(Boolean markDirectionEquals) {
		this.markDirectionEquals = markDirectionEquals;
	}

	public Boolean getContamination() {
		return contamination;
	}

	public void setContamination(Boolean contamination) {
		this.contamination = contamination;
	}

	public Boolean getEnough() {
		return isEnough;
	}

	public void setEnough(Boolean enough) {
		isEnough = enough;
	}
}
