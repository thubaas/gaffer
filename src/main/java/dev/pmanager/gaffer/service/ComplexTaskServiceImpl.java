package dev.pmanager.gaffer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.dto.SubtaskDto;
import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.mapper.ComplexTaskMapper;
import dev.pmanager.gaffer.mapper.SubtaskMapper;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.repository.ComplexTaskRepository;
import dev.pmanager.gaffer.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ComplexTaskServiceImpl implements ComplexTaskService {
	
	private final ComplexTaskRepository complexTaskRepository;
	private final MemberRepository memberRepository;
	private final ComplexTaskMapper complexTaskMapper;
	private final SubtaskMapper subtaskMapper;

	@Override
	public ComplexTaskDto getTaskById(String taskId) {
		log.info("Retrieving ComplexTask ID :{}", taskId);
		ComplexTask complexTask = complexTaskRepository.findById(taskId).get();
		ComplexTaskDto taskDto = complexTaskMapper.map(complexTask);
		return taskDto;
	}

	@Override
	public ComplexTaskDto getTaskByTitle(String title) {
		log.debug("Retrieving ComplexTask by Title : {}", title);
		ComplexTask complexTask = complexTaskRepository.findByTitle(title);
		ComplexTaskDto taskDto = complexTaskMapper.map(complexTask);
		return taskDto;
	}

	@Override
	public List<ComplexTaskDto> getTasksByUser(String userId) {
		log.debug("Retrive Complex Tasks by Assignee ID : {}", userId);
		Member assignee = memberRepository.findById(userId).get();
		List<ComplexTask> tasksByAssignee = complexTaskRepository.findAllByAssignee(assignee);
		List<ComplexTaskDto> mappedTasks = mapTasks(tasksByAssignee);
		return mappedTasks;
	}

	@Override
	public List<ComplexTaskDto> getTasksByStatus(String taskStatus) {
		log.debug("Retrieving Complex Tasks by Status : {}", taskStatus);
		Status status = Status.valueOf(taskStatus);
		List<ComplexTask> tasksByStatus = complexTaskRepository.findAllByStatus(status);
		List<ComplexTaskDto> mappedTasks = mapTasks(tasksByStatus);
		return mappedTasks;
	}

	@Override
	public List<ComplexTaskDto> getTasksByPriority(String taskPriority) {
		log.debug("Retrieving Complex Tasks by Priority : {}", taskPriority);
		Priority priority = Priority.valueOf(taskPriority);
		List<ComplexTask> tasksByPriority = complexTaskRepository.findAllByPriority(priority);
		List<ComplexTaskDto> mappedTasks = mapTasks(tasksByPriority);
		return mappedTasks;
	}

	@Override
	public List<SubtaskDto> getSubTasks(String parentId) {
		log.debug("Retreiving Sub Tasks for Task ID : {}", parentId);
		ComplexTask task = complexTaskRepository.findById(parentId).get();
		List<SubtaskDto> subtasks = task.getSubtasks()
				.stream()
				.map(subtask -> subtaskMapper.map(subtask))
				.collect(Collectors.toList());
		return subtasks;
	}

	@Override
	public ComplexTaskDto addTask(ComplexTaskDto taskDto) {
		log.debug("Adding Complex Task : {}", taskDto);
		ComplexTask task = complexTaskMapper.map(taskDto);
		ComplexTask savedTask = complexTaskRepository.save(task);
		return complexTaskMapper.map(savedTask);
	}

	@Override
	public ComplexTaskDto updateTask(String taskId, ComplexTaskDto taskDto) {
		log.debug("Updating Complex Task : {}", taskDto);
		ComplexTask task = complexTaskMapper.map(taskDto);
		ComplexTask savedTask = complexTaskRepository.save(task);
		return complexTaskMapper.map(savedTask);
	}

	@Override
	public void deleteTask(String taskId) {
		log.debug("Deleting Complex Task ID : {}", taskId);
		complexTaskRepository.deleteById(taskId);
	}
	
	private List<ComplexTaskDto> mapTasks(List<ComplexTask> tasksByAssignee) {
		List<ComplexTaskDto> mappedTasks = tasksByAssignee
				.stream()
				.map(task -> complexTaskMapper.map(task))
				.collect(Collectors.toList());
		return mappedTasks;
	}

}
