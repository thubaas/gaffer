package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.model.ComplexTask;

@Mapper(componentModel = "spring")
public abstract class ComplexTaskMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "title", source = "complexTaskDto.title")
	@Mapping(target = "description", source = "complexTaskDto.description")
	@Mapping(target = "startDate", source = "complexTaskDto.startDate")
	@Mapping(target = "finishDate", source = "complexTaskDto.finishDate")
	@Mapping(target = "assignee", source = "complexTaskDto.assignee")
	@Mapping(target = "priority", source = "complexTaskDto.priority")
	@Mapping(target = "precedent", source = "complexTaskDto.precedent")
	@Mapping(target = "status", source = "complexTaskDto.status")
	public abstract ComplexTask map(ComplexTaskDto complexTaskDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "title", source = "complexTask.title")
	@Mapping(target = "description", source = "complexTask.description")
	@Mapping(target = "startDate", source = "complexTask.startDate")
	@Mapping(target = "finishDate", source = "complexTask.finishDate")
	@Mapping(target = "assignee", source = "complexTask.assignee")
	@Mapping(target = "priority", source = "complexTask.priority")
	@Mapping(target = "precedent", source = "complexTask.precedent")
	@Mapping(target = "status", source = "complexTask.status")
	public abstract ComplexTaskDto map(ComplexTask complexTask);

}
