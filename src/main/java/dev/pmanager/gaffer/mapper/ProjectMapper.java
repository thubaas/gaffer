package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.ProjectDto;
import dev.pmanager.gaffer.model.Project;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {
	
//	@Mapping(target = "id", ignore = true)
//	@Mapping(target = "startDate", source = "projectDto.startDate")
//	@Mapping(target = "finishDate", source = "projectDto.finishDate")
//	@Mapping(target = "status", source = "projectDto.status")
//	@Mapping(target = "manager", source = "projectDto.manager")
//	@Mapping(target = "leader", source = "projectDto.leader")
//	@Mapping(target = "tasks", source = "projectDto.tasks")
//	public abstract Project map(ProjectDto projectDto);
//	
//	@Mapping(target = "id", ignore = true)
//	@Mapping(target = "startDate", source = "project.startDate")
//	@Mapping(target = "finishDate", source = "project.finishDate")
//	@Mapping(target = "status", source = "project.status")
//	@Mapping(target = "manager", source = "project.manager")
//	@Mapping(target = "leader", source = "project.leader")
//	@Mapping(target = "tasks", source = "project.tasks")
//	public abstract ProjectDto map(Project project);

}
