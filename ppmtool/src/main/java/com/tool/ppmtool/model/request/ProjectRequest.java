package com.tool.ppmtool.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProjectRequest {
	
	@NotNull
	String projectName;
	@NotNull
	@Size(min=4, max=5)
	String projectIdentifier;
	@NotNull
	String description;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
