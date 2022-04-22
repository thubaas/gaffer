package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.SubTaskDto;
import dev.pmanager.gaffer.model.SubTask;

@Mapper
public abstract class SubTaskMapper {
		
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "title", source = "subTaskDto.title")
	@Mapping(target = "description", source = "subTaskDto.description")
	@Mapping(target = "startDate", source = "subTaskDto.startDate")
	@Mapping(target = "finishDate", source = "subTaskDto.finishDate")
	@Mapping(target = "assignee", source = "subTaskDto.assignee")
	@Mapping(target = "priority", source = "subTaskDto.priority")
	@Mapping(target = "precedent", source = "subTaskDto.precedent")
	@Mapping(target = "status", source = "subTaskDto.status")
	@Mapping(target = "parentTask", source = "subTaskDto.parentTask")
	public abstract SubTask map(SubTaskDto subTaskDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "title", source = "subTask.title")
	@Mapping(target = "description", source = "subTask.description")
	@Mapping(target = "startDate", source = "subTask.startDate")
	@Mapping(target = "finishDate", source = "subTask.finishDate")
	@Mapping(target = "assignee", source = "subTask.assignee")
	@Mapping(target = "priority", source = "subTask.priority")
	@Mapping(target = "precedent", source = "subTask.precedent")
	@Mapping(target = "status", source = "subTask.status")
	@Mapping(target = "parentTask", source = "subTask.parentTask")
	public abstract SubTaskDto map(SubTask subTask);

}
