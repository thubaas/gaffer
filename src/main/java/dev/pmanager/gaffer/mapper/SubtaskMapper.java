package dev.pmanager.gaffer.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.SubtaskDto;
import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Subtask;
import dev.pmanager.gaffer.repository.ComplexTaskRepository;
import dev.pmanager.gaffer.repository.MemberRepository;
import dev.pmanager.gaffer.repository.SubtaskRepository;

@Mapper(componentModel = "spring")
public abstract class SubtaskMapper {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private SubtaskRepository subtaskRepository;
	@Autowired
	private ComplexTaskRepository complexTaskRepository;
		
	@Mapping(target = "id", source = "subtaskDto.id")
	@Mapping(target = "title", source = "subtaskDto.title")
	@Mapping(target = "description", source = "subtaskDto.description")
	@Mapping(target = "startDate", expression = "java(parseDate(subtaskDto.getStartDate()))")
	@Mapping(target = "finishDate", expression = "java(parseDate(subtaskDto.getFinishDate()))")
	@Mapping(target = "assignee", expression = "java(getAssignee(subtaskDto.getAssigneeId()))")
	@Mapping(target = "priority", source = "subtaskDto.priority")
	@Mapping(target = "precedent", expression = "java(getPrecedent(subtaskDto.getPrecedentId()))")
	@Mapping(target = "status", source = "subtaskDto.status")
	@Mapping(target = "parent", expression = "java(getParent(subtaskDto.getParentId()))")
	public abstract Subtask map(SubtaskDto subtaskDto);
	
	@Mapping(target = "id", source = "subtask.id")
	@Mapping(target = "title", source = "subtask.title")
	@Mapping(target = "description", source = "subtask.description") 
	@Mapping(target = "startDate", source = "subtask.startDate")
	@Mapping(target = "finishDate", source = "subtask.finishDate")
	@Mapping(target = "assigneeId", expression = "java(subtask.getAssignee().getId())")
	@Mapping(target = "priority", source = "subtask.priority")
	@Mapping(target = "precedentId", expression = "java(getPrecedentId(subtask))")
	@Mapping(target = "status", source = "subtask.status")
	@Mapping(target = "parentId", expression = "java(subtask.getParent().getId())")
	public abstract SubtaskDto map(Subtask subtask);
	
	protected Member getAssignee(String id) {
		return memberRepository.findById(id).get();
	}
	
	protected Subtask getPrecedent(String id) {
		if(id.isEmpty())
			return null;
		return subtaskRepository.findById(id).get();
	}
	
	protected String getPrecedentId(Subtask task) {
		return task.getPrecedent() == null ? "" : task.getPrecedent().getId();
	}
	
	protected ComplexTask getParent(String id) {
		return complexTaskRepository.findById(id).get();
	}
	
	protected LocalDate parseDate(String date) {
		return LocalDate.parse(date);
	}
	
	protected Priority parsePriority(String priority) {
		return Priority.valueOf(priority);
	}

}
