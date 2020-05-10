package com.tool.ppmtool.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.ppmtool.domain.Backlog;
import com.tool.ppmtool.domain.Project;
import com.tool.ppmtool.exception.project.ProjectIdException;
import com.tool.ppmtool.model.response.ProjectResponse;
import com.tool.ppmtool.repository.ProjectRepository;
import com.tool.ppmtool.service.ProjectService;
import com.tool.ppmtool.shared.dto.ProjectDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;


	@Override
	public ProjectDTO findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) 
				throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exists");
		
		ProjectDTO returnValue = new ProjectDTO();
		BeanUtils.copyProperties(project, returnValue);
		return returnValue;
	}

	@Override
	public List<ProjectDTO> findAllProjects() {
		Iterable<Project> storedProjects = projectRepository.findAll();
		List<Project> list = 
				  StreamSupport.stream(storedProjects.spliterator(), false)
				    .collect(Collectors.toList());
		List<ProjectDTO> returnValue = new ArrayList<>();
		
		java.lang.reflect.Type listType = new TypeToken<List<ProjectDTO>>() {}.getType();
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(list, listType);
		
		return returnValue;
	}

	@Override
	public void deleteProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if (project == null)
			throw new ProjectIdException("No project with ID '" + projectId.toUpperCase() + "'");
		
		projectRepository.deleteById(project.getId());
	}


	@Override
	public ProjectDTO saveOrUpdateProject(ProjectDTO projectDTO) {
		Project project = new Project();
		Project storedProjectDetails = new Project();
		BeanUtils.copyProperties(projectDTO, project);
		
		project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		storedProjectDetails = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
		if(storedProjectDetails != null)
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		
		{
			Backlog backlog = new Backlog();
			project.setBacklog(backlog);
			backlog.setProject(project);
			backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		}
		
		
		storedProjectDetails = projectRepository.save(project);
		
		ProjectDTO returnValue = new ProjectDTO();
		BeanUtils.copyProperties(storedProjectDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public ProjectDTO updateProject(ProjectDTO projectDTO) {
		Project newProject = new Project();
		
		BeanUtils.copyProperties(projectDTO, newProject);
		
		newProject.setProjectIdentifier(newProject.getProjectIdentifier().toUpperCase());
		Project projectEntity = projectRepository.findByProjectIdentifier(newProject.getProjectIdentifier());
		
		if (projectEntity == null)
			throw new ProjectIdException("No project with ID '" + newProject.getProjectIdentifier() + "'");
		
		projectEntity.setDescription(newProject.getDescription());
		projectEntity.setProjectName(newProject.getProjectName());
		Project updatedProject = projectRepository.save(projectEntity);
		ProjectDTO returnValue = new ProjectDTO();
		BeanUtils.copyProperties(updatedProject, returnValue);
		return returnValue;
	}

}
