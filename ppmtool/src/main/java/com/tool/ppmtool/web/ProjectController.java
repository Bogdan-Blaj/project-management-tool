package com.tool.ppmtool.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.ppmtool.domain.Project;
import com.tool.ppmtool.model.request.ProjectRequest;
import com.tool.ppmtool.model.response.ProjectResponse;
import com.tool.ppmtool.service.ValidationService;
import com.tool.ppmtool.shared.dto.ProjectDTO;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import com.tool.ppmtool.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ValidationService validationService;
	
//	@PostMapping
//	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
//		
//		ResponseEntity<?> errorMap = validationService.checkValid(result);
//		if(errorMap != null) 
//				return errorMap;
//		
//		ProjectDTO projectDTO = new ProjectDTO();
//		ProjectDTO createdProject = new ProjectDTO();
//		
//		BeanUtils.copyProperties(project, projectDTO);
//		
//		Project returnValue = projectService.saveOrUpdateProject(project);
//		
//		return new ResponseEntity<Project>(returnValue, HttpStatus.CREATED);
//	}
	
	
	
	@PostMapping
	public ResponseEntity<?> createNewProject2(@Valid @RequestBody ProjectRequest project, BindingResult result){
		
		ResponseEntity<?> errorMap = validationService.checkValid(result);
		if(errorMap != null) 
				return errorMap;
		
		ProjectDTO projectDTO = new ProjectDTO();
		ProjectDTO createdProject = new ProjectDTO();
		
		BeanUtils.copyProperties(project, projectDTO);
		
		createdProject = projectService.saveOrUpdateProject(projectDTO);
		
		return new ResponseEntity<ProjectDTO>(createdProject, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectbyId(@PathVariable String projectId){
		
		ProjectDTO projectDTO = projectService.findProjectByIdentifier(projectId);
		ProjectResponse returnValue = new ProjectResponse();
		
		BeanUtils.copyProperties(projectDTO, returnValue);
		
		return new ResponseEntity<ProjectResponse>(returnValue, HttpStatus.OK);
	}
	
	@GetMapping
	public Iterable<ProjectResponse> getAllProjects(){
		List<ProjectDTO> storedProjects = projectService.findAllProjects();
		List<ProjectResponse> returnValue = new ArrayList<>();
		java.lang.reflect.Type listType = new TypeToken<List<ProjectResponse>>() {}.getType();
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(storedProjects, listType);
		
		return returnValue;
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProjectByIdentifier(projectId);
		
		return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted", HttpStatus.OK);
	}
	
//	@PutMapping
//	public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result){
//		
//		ResponseEntity<?> errorMap = validationService.checkValid(result);
//		if(errorMap != null) 
//				return errorMap;
//		
//		
//		Project returnValue = projectService.updateProject(project);
//		
//		return new ResponseEntity<Project>(returnValue, HttpStatus.CREATED);
//	}
	
	
	@PutMapping
	public ResponseEntity<?> updateProject2(@Valid @RequestBody ProjectRequest project, BindingResult result){
		
		ResponseEntity<?> errorMap = validationService.checkValid(result);
		if(errorMap != null) 
				return errorMap;
		
		ProjectDTO projectDTO = new ProjectDTO();
		BeanUtils.copyProperties(project, projectDTO);
		
		ProjectDTO updatedProject = projectService.updateProject(projectDTO);
		ProjectResponse returnValue = new ProjectResponse();
		BeanUtils.copyProperties(updatedProject, returnValue);
		
		return new ResponseEntity<ProjectResponse>(returnValue, HttpStatus.CREATED);
	}
	
	
	
	
	
}
