package dev.pmanager.gaffer.service;

import java.util.List;

import dev.pmanager.gaffer.dto.SubtaskDto;

public interface SubtaskService {
	
	SubtaskDto addSubtask(String taskId, SubtaskDto subtaskDto);
	SubtaskDto getSubtaskById(String subtaskId);
	void deleteSubtask(String subtaskId);
	SubtaskDto updateSubtask(String subtaskId, SubtaskDto subtaskDto);
	SubtaskDto getSubtaskByTitle(String title);
	List<SubtaskDto> getTasksByUser(String userId);
	List<SubtaskDto> getTasksByStatus(String taskStatus);
	List<SubtaskDto> getTasksByPriority(String taskPriority);

}
