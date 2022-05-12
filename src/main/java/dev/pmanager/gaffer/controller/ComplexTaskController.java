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

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.service.ComplexTaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("gaffer/projects/{id}/tasks")
@RestController
public class ComplexTaskController {
	
	private final ComplexTaskService complexTaskService;
	
	@GetMapping("/{taskId}")
	public ResponseEntity<ComplexTaskDto> getTask(@PathVariable String taskId) {
		log.debug("Retrieving Complex Task ID : {}", taskId);
		ComplexTaskDto task = complexTaskService.getTaskById(taskId);
		return ResponseEntity.ok(task);
	}
	
	@PostMapping 
	public ResponseEntity<ComplexTaskDto> addComplexTask(@RequestBody ComplexTaskDto complexTaskDto){
		log.debug("Adding Complex Task  {}", complexTaskDto);
		ComplexTaskDto savedTask = complexTaskService.addTask(complexTaskDto);
		return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/assignees/{userId}")
	public ResponseEntity<List<ComplexTaskDto>> getTasksByUser(@PathVariable String userId){
		log.debug("Retrieving Tasks by User ID : {}", userId);
		List<ComplexTaskDto> tasksByUser = complexTaskService.getTasksByUser(userId);
		return ResponseEntity.ok(tasksByUser);
		
	}
	
	@GetMapping("/priorities/{priority}")
	public ResponseEntity<List<ComplexTaskDto>> getTasksByPriority(@PathVariable String priority){
		log.debug("Retrieving Tasks by Priority ID : {}", priority);
		List<ComplexTaskDto> tasksByPriority = complexTaskService.getTasksByPriority(priority.toUpperCase());
		return ResponseEntity.ok(tasksByPriority);
		
	}
	
	@GetMapping("/statuses/{status}")
	public ResponseEntity<List<ComplexTaskDto>> getTasksByStatus(@PathVariable String status){
		log.debug("Retrieving Tasks by Status : {}", status);
		List<ComplexTaskDto> tasksByStatus = complexTaskService.getTasksByStatus(status.toUpperCase());
		return ResponseEntity.ok(tasksByStatus);
		
	}
	
	@GetMapping("/titles/{title}/subtasks")
	public ResponseEntity<ComplexTaskDto> getTaskByTitle(@PathVariable String title){
		log.debug("Retrieving Task by Title : {}", title);
		ComplexTaskDto taskByTitle = complexTaskService.getTaskByTitle(title);
		return ResponseEntity.ok(taskByTitle);
		
	}
	
	@PutMapping("/{taskId}")
	public ResponseEntity<ComplexTaskDto> updateTask(
			@PathVariable String taskId, 
			@RequestBody ComplexTaskDto complexTaskDto) {
		log.debug("Updating Complex Task : {}", complexTaskDto);
		ComplexTaskDto updatedTask = complexTaskService.updateTask(taskId, complexTaskDto);
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
		log.debug("Deleting a Task by ID : {}", taskId);
		complexTaskService.deleteTask(taskId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
