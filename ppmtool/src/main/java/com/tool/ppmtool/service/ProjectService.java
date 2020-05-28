package com.tool.ppmtool.service;

import java.util.List;

import com.tool.ppmtool.domain.Project;
import com.tool.ppmtool.shared.dto.ProjectDTO;

public interface ProjectService {

	Project saveOrUpdateProject(Project project);
	
	ProjectDTO saveOrUpdateProject(ProjectDTO projectDTO);
	
	ProjectDTO findProjectByIdentifier(String projectId);
	
	List<ProjectDTO> findAllProjects();
	
	void deleteProjectByIdentifier(String projectId);
	
	Project updateProject(Project project);
	
	ProjectDTO updateProject(ProjectDTO project);
}
