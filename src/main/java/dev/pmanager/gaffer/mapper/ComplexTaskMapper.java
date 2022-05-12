package dev.pmanager.gaffer.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.dto.SubtaskDto;
import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Subtask;
import dev.pmanager.gaffer.repository.ComplexTaskRepository;
import dev.pmanager.gaffer.repository.MemberRepository;
import dev.pmanager.gaffer.repository.SubtaskRepository;

@Mapper(componentModel = "spring")
public abstract class ComplexTaskMapper {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private SubtaskRepository subtaskRepository;
	@Autowired
	private ComplexTaskRepository complexTaskRepository;
	@Autowired
	private SubtaskMapper subtaskMapper;
	
	@Mapping(target = "id", source = "complexTaskDto.id")
	@Mapping(target = "title", source = "complexTaskDto.title")
	@Mapping(target = "description", source = "complexTaskDto.description")
	@Mapping(target = "startDate", expression = "java(createDate(complexTaskDto.getStartDate()))")
	@Mapping(target = "finishDate", expression = "java(createDate(complexTaskDto.getFinishDate()))")
	@Mapping(target = "assignee", expression = "java(getAssignee(complexTaskDto.getAssigneeId()))")
	@Mapping(target = "priority", expression = "java(parsePriority(complexTaskDto.getPriority()))")
	@Mapping(target = "precedent", expression = "java(getPrecedent(complexTaskDto.getPrecedentId()))")
	@Mapping(target = "status", source = "complexTaskDto.status")
	@Mapping(target = "subtasks", ignore = true)
	public abstract ComplexTask map(ComplexTaskDto complexTaskDto);
	
	@Mapping(target = "id", source = "complexTask.id")
	@Mapping(target = "title", source = "complexTask.title")
	@Mapping(target = "description", source = "complexTask.description")
	@Mapping(target = "startDate", source = "complexTask.startDate")
	@Mapping(target = "finishDate", source = "complexTask.finishDate")
	@Mapping(target = "assigneeId", expression = "java(complexTask.getAssignee().getId())")
	@Mapping(target = "priority", source = "complexTask.priority")
	@Mapping(target = "precedentId", expression = "java(getPrecedentId(complexTask))")
	@Mapping(target = "status", source = "complexTask.status")
	@Mapping(target = "subtasks", expression = "java(mapSubtasks(complexTask.getSubtasks()))")
	public abstract ComplexTaskDto map(ComplexTask complexTask);
	
	protected Member getAssignee(String id) {
		return memberRepository.findById(id).get();
	}
	
	protected ComplexTask getPrecedent(String id) {
		if(id.isEmpty())
			return null;
		return complexTaskRepository.findById(id).get();
	}
	
	protected String getPrecedentId(ComplexTask task) {
		return task.getPrecedent() == null ? "" : task.getPrecedent().getId();
	}
	

	protected LocalDate createDate(String date) {
		return LocalDate.parse(date);
	}
	
	protected Priority parsePriority(String priority) {
		return Priority.valueOf(priority);
	}
	
	protected List<SubtaskDto> mapSubtasks(List<Subtask> subtasks) {
		return subtasks
				.stream()
				.map(subtask -> subtaskMapper.map(subtask))
				.collect(Collectors.toList());
	}

}
