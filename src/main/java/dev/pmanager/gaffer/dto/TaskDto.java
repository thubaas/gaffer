package dev.pmanager.gaffer.dto;

import java.util.Date;

import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
	
	private String id;
	private String title;
	private String description;
	private Date startDate;
	private Date finishDate;
	private MemberDto assignee;
	private Priority priority;
	private TaskDto precedent;
	private Status status;

}
