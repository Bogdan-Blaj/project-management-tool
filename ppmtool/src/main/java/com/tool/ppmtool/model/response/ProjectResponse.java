package com.tool.ppmtool.model.response;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tool.ppmtool.domain.Backlog;

public class ProjectResponse {
	
	private Long id;
	
//	@NotNull
	String projectName;
//	@NotNull
//	@Size(min=4, max=5)
	String projectIdentifier;
//	@NotNull
	String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start_date;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end_date;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date created_At;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date updated_At;
	
//	private Backlog backlog;
	
	

//	public Backlog getBacklog() {
//		return backlog;
//	}
//
//	public void setBacklog(Backlog backlog) {
//		this.backlog = backlog;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}


}