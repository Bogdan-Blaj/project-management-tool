package com.tool.ppmtool.service;

import com.tool.ppmtool.domain.ProjectTask;

public interface ProjectTaskService {

	ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask);

	Iterable<ProjectTask> findBacklogById(String backlog_id);
	
	ProjectTask findPTByProjectSequence(String backlog_id, String sequence);
	
	ProjectTask updateProjectTask(String backlog_id, String projectSequence, ProjectTask projectTask);
	
	void deleteProjectTask(String backlog_id, String sequence);
}
