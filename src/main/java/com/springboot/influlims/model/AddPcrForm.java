package com.springboot.influlims.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class AddPcrForm {

	private Long extractions[];

	private Long reagentId;

	private String pcrName;

	private MultipartFile pcrProtocol;

	private MultipartFile pcrReport;

	private Date pcrDate;

	public Long[] getExtractions() {
		return extractions;
	}

	public void setExtractions(Long[] extractions) {
		this.extractions = extractions;
	}

	public Long getReagentId() {
		return reagentId;
	}

	public void setReagentId(Long reagentId) {
		this.reagentId = reagentId;
	}

	public String getPcrName() {
		return pcrName;
	}

	public void setPcrName(String pcrName) {
		this.pcrName = pcrName;
	}

	public MultipartFile getPcrProtocol() {
		return pcrProtocol;
	}

	public void setPcrProtocol(MultipartFile pcrProtocol) {
		this.pcrProtocol = pcrProtocol;
	}

	public MultipartFile getPcrReport() {
		return pcrReport;
	}

	public void setPcrReport(MultipartFile pcrReport) {
		this.pcrReport = pcrReport;
	}

	public Date getPcrDate() {
		return pcrDate;
	}

	public void setPcrDate(Date pcrDate) {
		this.pcrDate = pcrDate;
	}
}
