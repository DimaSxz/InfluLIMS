package com.springboot.influlims.model;

public class ManageReagentsForm {

	private Long reagentId;

	private String reagentName;

	private String function;

	private String action;

	public Long getReagentId() {
		return reagentId;
	}

	public void setReagentId(Long reagentId) {
		this.reagentId = reagentId;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
