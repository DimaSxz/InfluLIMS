package com.springboot.influlims.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class AddExtractionForm {

	private Long samples[];

	private Long reagentId;

	private String extractionName;

	private Integer volumeIn;

	private Integer volumeOut;

	private Date extractionDate;

	private MultipartFile extractionFile;

	public Long[] getSamples() {
		return samples;
	}

	public void setSamples(Long[] samples) {
		this.samples = samples;
	}

	public Long getReagentId() {
		return reagentId;
	}

	public void setReagentId(Long reagentId) {
		this.reagentId = reagentId;
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

	public Date getExtractionDate() {
		return extractionDate;
	}

	public void setExtractionDate(Date extractionDate) {
		this.extractionDate = extractionDate;
	}

	public MultipartFile getExtractionFile() {
		return extractionFile;
	}

	public void setExtractionFile(MultipartFile extractionFile) {
		this.extractionFile = extractionFile;
	}
}
