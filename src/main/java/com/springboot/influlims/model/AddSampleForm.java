package com.springboot.influlims.model;

import com.springboot.influlims.entity.enums.Gender;
import com.springboot.influlims.entity.enums.Vaccine;

import java.util.Date;

public class AddSampleForm {

//	PatientEntity
	private String patientName;
	private Short patientAge;
	private Byte patientMonths;
	private Date patientDOB;

//	private Gender patientGender;
	private String patientGender;
//	private Vaccine isVaccinated;
	private String isVaccinated;

	private Integer regionUNID;
// ProviderEntity
	private Long providerId;
//	ProjectEntity
	private Long projectId;
//	SampleEntity
	private String labNum;
	private Long caseHistory;
	private Long numInProject;
	private String externalNum;
	private String sampleType;
	private String samplePm;
	private String sampleFunc;
	private Integer volumeIn;
	private Date dateReceipt;
	private Date dateDisease;
	private Date dateCollection;
	private String season;
	private String notes;
//	ContainerEntity
	private String isPropered;
	private String isMarked;
	private String markDirectionEquals;
	private String contamination;
	private String isEnough;

	public Integer getRegionUNID() {
		return regionUNID;
	}

	public void setRegionUNID(Integer regionUNID) {
		this.regionUNID = regionUNID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Short getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Short patientAge) {
		this.patientAge = patientAge;
	}

	public Byte getPatientMonths() {
		return patientMonths;
	}

	public void setPatientMonths(Byte patientMonths) {
		this.patientMonths = patientMonths;
	}

	public Date getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

//	public Gender getPatientGender() {
//		return patientGender;
//	}

//	public void setPatientGender(Gender patientGender) {
//		this.patientGender = patientGender;
//	}
//	public void setPatientGender(String patientGender) {
//		this.patientGender = Gender.valueOf(patientGender);
//	}


	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

//	public Vaccine getIsVaccinated() {
//		return isVaccinated;
//	}
//
//	public void setIsVaccinated(Vaccine isVaccinated) {
//		this.isVaccinated = isVaccinated;
//	}

	public String getIsVaccinated() {
		return isVaccinated;
	}

	public void setIsVaccinated(String isVaccinated) {
		this.isVaccinated = isVaccinated;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getLabNum() {
		return labNum;
	}

	public void setLabNum(String labNum) {
		this.labNum = labNum;
	}

	public Long getCaseHistory() {
		return caseHistory;
	}

	public void setCaseHistory(Long caseHistory) {
		this.caseHistory = caseHistory;
	}

	public Long getNumInProject() {
		return numInProject;
	}

	public void setNumInProject(Long numInProject) {
		this.numInProject = numInProject;
	}

	public String getExternalNum() {
		return externalNum;
	}

	public void setExternalNum(String externalNum) {
		this.externalNum = externalNum;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

//	public Boolean getSamplePm() {
//		return samplePm;
//	}
//
//	public void setSamplePm(Boolean samplePm) {
//		this.samplePm = samplePm;
//	}

	public String getSamplePm() {
		return samplePm;
	}

	public void setSamplePm(String samplePm) {
		this.samplePm = samplePm;
	}

	public String getSampleFunc() {
		return sampleFunc;
	}

	public void setSampleFunc(String sampleFunc) {
		this.sampleFunc = sampleFunc;
	}

	public Integer getVolumeIn() {
		return volumeIn;
	}

	public void setVolumeIn(Integer volumeIn) {
		this.volumeIn = volumeIn;
	}

	public Date getDateReceipt() {
		return dateReceipt;
	}

	public void setDateReceipt(Date dateReceipt) {
		this.dateReceipt = dateReceipt;
	}

	public Date getDateDisease() {
		return dateDisease;
	}

	public void setDateDisease(Date dateDisease) {
		this.dateDisease = dateDisease;
	}

	public Date getDateCollection() {
		return dateCollection;
	}

	public void setDateCollection(Date dateCollection) {
		this.dateCollection = dateCollection;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getIsPropered() {
		return isPropered;
	}

	public void setIsPropered(String isPropered) {
		this.isPropered = isPropered;
	}

	public String getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(String isMarked) {
		this.isMarked = isMarked;
	}

	public String getMarkDirectionEquals() {
		return markDirectionEquals;
	}

	public void setMarkDirectionEquals(String markDirectionEquals) {
		this.markDirectionEquals = markDirectionEquals;
	}

	public String getContamination() {
		return contamination;
	}

	public void setContamination(String contamination) {
		this.contamination = contamination;
	}

	public String getIsEnough() {
		return isEnough;
	}

	public void setIsEnough(String isEnough) {
		this.isEnough = isEnough;
	}
}
