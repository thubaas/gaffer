package dev.pmanager.gaffer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.ProjectDto;
import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.mapper.ProjectMapper;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.repository.ManagerRepository;
import dev.pmanager.gaffer.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ManagerRepository managerRepository;
	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	
	@Override
	public ProjectDto addProject(ProjectDto projectDto) {
		Project mappedProject = projectMapper.map(projectDto);
		Project savedProject = projectRepository.save(mappedProject);
		log.info("Added project : {}", savedProject);
		return projectMapper.map(savedProject);
	}

	@Override
	public ProjectDto getProject(String projectId) {
		Project project = projectRepository.findById(projectId).get();
		ProjectDto mappedProject = projectMapper.map(project);
		log.debug("Retrived Project : {}", mappedProject);
		return mappedProject;
	}

	@Override
	public ProjectDto updateProject(String projectId, ProjectDto projectDto) {
		log.debug("Updating Project : {}", projectDto);
		Project project = projectMapper.map(projectDto);
		Project savedProject = projectRepository.save(project);
		return projectMapper.map(savedProject);
	}

	@Override
	public void deleteProject(String projectId) {
		log.debug("Deleting Project ID : {}", projectId);
		projectRepository.deleteById(projectId);
	}

	@Override
	public List<ProjectDto> getProjectsByManager(String managerId) {
		Manager manager = managerRepository.findById(managerId).get();
		log.debug("Retrieving Projects by manager : {}", manager);
		List<Project> projectsByManager = projectRepository
				.findByManager(manager);
		List<ProjectDto> mappedProjects = projectsByManager
				.stream()
				.map(p -> projectMapper.map(p))
				.collect(Collectors.toList());
		return mappedProjects;
	}

	@Override
	public List<ProjectDto> getProjectsByStatus(String statusLiteral) {
		Status status = Status.valueOf(statusLiteral.toUpperCase());
		log.debug("Retrieving Projects by status : {}", status);
		List<Project> projectsByStatus = projectRepository
				.findByStatus(status);
		List<ProjectDto> mappedProjects = projectsByStatus
				.stream()
				.map(p -> projectMapper.map(p))
				.collect(Collectors.toList());
		return mappedProjects;
	}

}
