package com.springboot.influlims.model;

public class ManageProvidersProjectsForm {

	private Long providerId;

	private Long projects[];

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long[] getProjects() {
		return projects;
	}

	public void setProjects(Long[] projects) {
		this.projects = projects;
	}
}
