package dev.pmanager.gaffer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.SubtaskDto;
import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.mapper.SubtaskMapper;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Subtask;
import dev.pmanager.gaffer.repository.ComplexTaskRepository;
import dev.pmanager.gaffer.repository.MemberRepository;
import dev.pmanager.gaffer.repository.SubtaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class SubtaskServiceImpl implements SubtaskService {
	
	private final MemberRepository memberRepository;
	private final ComplexTaskRepository complexTaskRepository;
	private final SubtaskRepository subtaskRepository;
	private final SubtaskMapper subtaskMapper;
	
	@Override
	public SubtaskDto addSubtask(String taskId, SubtaskDto subtaskDto) {
		log.info("Adding Subtask : {}",subtaskDto);
		Subtask subtask = subtaskMapper.map(subtaskDto);
		Subtask savedSubtask = subtaskRepository.save(subtask);
		ComplexTask parentTask = complexTaskRepository.findById(taskId).get();
		parentTask.getSubtasks().add(savedSubtask);
		complexTaskRepository.save(parentTask);
		return subtaskMapper.map(savedSubtask);
	}

	@Override
	public SubtaskDto getSubtaskById(String subtaskId) {
		log.info("Retrieving Subtask by ID : {}", subtaskId);
		Subtask subtask = subtaskRepository.findById(subtaskId).get();
		return subtaskMapper.map(subtask);
	}

	@Override
	public void deleteSubtask(String subtaskId) {
		log.info("Deleting Subtask ID : {}", subtaskId);
		subtaskRepository.deleteById(subtaskId);
	}

	@Override
	public SubtaskDto updateSubtask(String subtaskId, SubtaskDto subtaskDto) {
		log.info("Updating Subtask : {}", subtaskDto);
		Subtask subtask = subtaskMapper.map(subtaskDto);
		Subtask savedSubtask = subtaskRepository.save(subtask);
		
		String parentId = savedSubtask.getParent().getId();
		ComplexTask parent = complexTaskRepository.findById(parentId).get();

		parent.getSubtasks().removeIf(item -> item.getId().equals(savedSubtask.getId()));
		parent.getSubtasks().add(savedSubtask);
		complexTaskRepository.save(parent);
		return subtaskMapper.map(savedSubtask);
	}

	@Override
	public SubtaskDto getSubtaskByTitle(String title) {
		log.info("Retrieving Subtask by Title : {}", title);
		Subtask subtask = subtaskRepository.findByTitle(title);
		return  subtaskMapper.map(subtask);
	}

	@Override
	public List<SubtaskDto> getTasksByUser(String userId) {
		log.info("Retrieving Subtasks by User ID : {}", userId);
		Member assignee = memberRepository.findById(userId).get();
		List<Subtask> subtasks = subtaskRepository.findAllByAssignee(assignee);
		List<SubtaskDto> mappedSubtasks = mapTasks(subtasks);
		return mappedSubtasks;
	}

	@Override
	public List<SubtaskDto> getTasksByStatus(String taskStatus) {
		log.info("Retrieving Subtasks by Status : {}", taskStatus);
		Status status = Status.valueOf(taskStatus);
		List<Subtask> subtasks = subtaskRepository.findAllByStatus(status);
		List<SubtaskDto> mappedSubtasks = mapTasks(subtasks);
		return mappedSubtasks;
	}

	@Override
	public List<SubtaskDto> getTasksByPriority(String taskPriority) {
		log.info("Retrieving Subtasks by Status : {}", taskPriority);
		Priority priority = Priority.valueOf(taskPriority);
		List<Subtask> subtasks = subtaskRepository.findAllByPriority(priority);
		List<SubtaskDto> mappedSubtasks = mapTasks(subtasks);
		return mappedSubtasks;
	}
	
	private List<SubtaskDto> mapTasks(List<Subtask> subtasks) {
		List<SubtaskDto> mappedSubtasks = subtasks
				.stream()
				.map(subtask -> subtaskMapper.map(subtask))
				.collect(Collectors.toList());
		return mappedSubtasks;
	}

}
