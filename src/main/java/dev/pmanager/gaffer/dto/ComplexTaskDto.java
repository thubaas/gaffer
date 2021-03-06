package dev.pmanager.gaffer.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplexTaskDto {
	private String id;
	private String title;
	private String description;
	private String startDate;
	private String finishDate;
	private String assigneeId;
	private String priority;
	private String precedentId;
	private String status;
	private Collection<SubtaskDto> subtasks; 

}
