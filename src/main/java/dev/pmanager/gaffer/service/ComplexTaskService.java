package dev.pmanager.gaffer.service;

import java.util.List;

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.dto.SubtaskDto;

public interface ComplexTaskService {
	
	ComplexTaskDto getTaskById(String taskId);
	ComplexTaskDto getTaskByTitle(String title);
	List<ComplexTaskDto> getTasksByUser(String userId);
	List<ComplexTaskDto> getTasksByStatus(String taskStatus);
	List<ComplexTaskDto> getTasksByPriority(String taskPriority);
	List<SubtaskDto> getSubTasks(String parentId);
	ComplexTaskDto addTask(ComplexTaskDto taskDto);
	ComplexTaskDto updateTask(String taskId, ComplexTaskDto taskDto);
	void deleteTask(String taskId);
	
}
