package dev.pmanager.gaffer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pmanager.gaffer.dto.ProjectDto;
import dev.pmanager.gaffer.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RequestMapping("/gaffer/projects")
@RestController
public class ProjectController {
	
	private final ProjectService projectService;
	
//	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping
	public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
		log.info("Creating Project : {}", projectDto);
		ProjectDto savedProject = projectService.addProject(projectDto);
		return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectDto> getProject(@PathVariable String projectId) {
		log.debug("Retrieving Project ID : {}", projectId);
		ProjectDto project = projectService.getProject(projectId);
		return ResponseEntity.ok(project);
	}
	
	@GetMapping("/statuses/{status}")
	public ResponseEntity<List<ProjectDto>> getProjectsByStatus(@PathVariable String status) {
		log.debug("Retrieving Projects by status : {}", status);
		List<ProjectDto> projectsByStatus = projectService.getProjectsByStatus(status);
		return ResponseEntity.ok(projectsByStatus);
	}
	
	@GetMapping("/managers/{managerId}")
	public ResponseEntity<List<ProjectDto>> getProjectsByManager(@PathVariable String managerId) {
		log.debug("Retrieving Projects by manager ID : {}", managerId);
		List<ProjectDto> projectsByManager = projectService.getProjectsByManager(managerId);
		return ResponseEntity.ok(projectsByManager);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable String projectId) {
		log.debug("Deleting Project ID : {}", projectId);
		projectService.deleteProject(projectId);
		return ResponseEntity.ok("Deleted Project");
		
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<ProjectDto> updateProject(@PathVariable String projectId, @RequestBody ProjectDto projectDto) {
		log.debug("Updating Project : {}", projectDto);
		ProjectDto updatedProject = projectService.updateProject(projectId, projectDto);
		return ResponseEntity.ok(updatedProject);
	}

}
