package dev.pmanager.gaffer.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.ProjectDto;
import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.repository.ManagerRepository;
import dev.pmanager.gaffer.repository.ProjectRepository;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {
	
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	@Mapping(target = "id", source = "projectDto.id")
	@Mapping(target = "title", source = "projectDto.title")
	@Mapping(target = "startDate", expression = "java(createDate(projectDto.getStartDate()))")
	@Mapping(target = "finishDate", expression = "java(createDate(projectDto.getFinishDate()))")
	@Mapping(target = "status", expression = "java(createStatus(projectDto))")
	@Mapping(target = "manager", expression = "java(getManager(projectDto))")
	@Mapping(target = "tasks", expression = "java(getTasks(projectDto))")
	public abstract Project map(ProjectDto projectDto);
	
	@Mapping(target = "id", source = "project.id")
	@Mapping(target = "title", source = "project.title")
	@Mapping(target = "startDate", expression = "java(project.getStartDate().toString())")
	@Mapping(target = "finishDate", expression = "java(project.getFinishDate().toString())")
	@Mapping(target = "status", source = "project.status")
	@Mapping(target = "managerId", expression = "java(project.getManager().getId())")
	@Mapping(target = "tasks", source = "project.tasks")
	public abstract ProjectDto map(Project project);
	
	protected Manager getManager(ProjectDto projectDto) {		
			return managerRepository
					.findById(projectDto.getManagerId())
					.get();
	}
	
	protected List<ComplexTask> getTasks(ProjectDto projectDto) {
		if(projectDto.getId() != null) {
			Project project = projectRepository.findById(projectDto.getId()).get();
			return project.getTasks();
		}
		return new ArrayList<ComplexTask>();
		
	}
	
	protected Status createStatus(ProjectDto projectDto) {
		return Status.valueOf(projectDto.getStatus());
	}
	
	protected LocalDate createDate(String date) {
		return LocalDate.parse(date);	
	}
	

}
