package dev.pmanager.gaffer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pmanager.gaffer.dto.SubtaskDto;
import dev.pmanager.gaffer.service.SubtaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(maxAge = 3600)
@AllArgsConstructor
@Slf4j
@RequestMapping("gaffer/projects/{id}/tasks/{taskId}/subtasks")
@RestController
public class SubtaskController {
	
	private final SubtaskService subtaskService;
	
	@GetMapping("/{subtaskId}")
	public ResponseEntity<SubtaskDto> getSubtaskById(@PathVariable String subtaskId) {
		log.info("Retrieving Subtask by ID : {}", subtaskId);
		SubtaskDto subtask = subtaskService.getSubtaskById(subtaskId);
		return ResponseEntity.ok(subtask);
	}
	
	@PostMapping
	public ResponseEntity<SubtaskDto> addSubtask(@PathVariable String taskId, @RequestBody SubtaskDto subtaskDto) {
		log.info("Adding Subtask : {}", subtaskDto);
		SubtaskDto subtask = subtaskService.addSubtask(taskId, subtaskDto);
		return new ResponseEntity<>(subtask, HttpStatus.CREATED);
		
	}
	
	@GetMapping("subtask-assignees/{assigneeId}")
	public ResponseEntity<List<SubtaskDto>> getSubtasksByUser(@PathVariable String assigneeId) {
		log.info("Retrieving Subtasks by user ID : {}", assigneeId);
		List<SubtaskDto> subtasksByAssignee = subtaskService.getTasksByUser(assigneeId);
		return ResponseEntity.ok(subtasksByAssignee);
	}
	
	@GetMapping("subtask-priorities/{priority}")
	public ResponseEntity<List<SubtaskDto>> getSubtasksByPriority(@PathVariable String priority) {
		log.info("Retrieving Subtasks by Priority : {}", priority);
		List<SubtaskDto> subtasksByPriority = subtaskService.getTasksByPriority(priority.toUpperCase());
		return ResponseEntity.ok(subtasksByPriority);
	}
	
	@GetMapping("subtask-statuses/{status}")
	public ResponseEntity<List<SubtaskDto>> getSubtasksByStatus(@PathVariable String status) {
		log.info("Retrieving Subtasks by Status : {}", status);
		List<SubtaskDto> subtasksByStatus = subtaskService.getTasksByStatus(status.toUpperCase());
		return ResponseEntity.ok(subtasksByStatus);
	}
	
	@PutMapping("/{subtaskId}")
	public ResponseEntity<SubtaskDto> updateTask(@PathVariable String subtaskId, @RequestBody SubtaskDto subtaskDto) {
		log.info("Updating Subtask Id : {}", subtaskDto);
		SubtaskDto updatedSubtask = subtaskService.updateSubtask(subtaskId, subtaskDto);
		return ResponseEntity.ok(updatedSubtask);
	}
	
	@DeleteMapping("/{subtaskId}")
	public ResponseEntity<String> deleteTask(@PathVariable String subtaskId) {
		log.info("Deleting subtask : {}", subtaskId);
		subtaskService.deleteSubtask(subtaskId);
		return ResponseEntity.ok("Subtask successfully deleted");
	}
	
	
	

}
