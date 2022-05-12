package dev.pmanager.gaffer.service;

import java.util.List;

import dev.pmanager.gaffer.dto.ProjectDto;

public interface ProjectService {
	
	ProjectDto addProject(ProjectDto projectDto);
	
	ProjectDto getProject(String projectId);
	
	List<ProjectDto> getProjectsByManager(String managerId);
	
	List<ProjectDto> getProjectsByStatus(String statusLiteral);
	
	ProjectDto updateProject(String projectId, ProjectDto projectDto);
	
	void deleteProject(String projectId);

}
