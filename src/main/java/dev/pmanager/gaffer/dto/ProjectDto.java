package dev.pmanager.gaffer.dto;

import java.util.Collection;
import java.util.Date;

import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.model.Leader;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
	
	private String id;
	private Date startDate;
	private Date finishDate;
	private Status status;
	private Manager manager;
	private Leader leader;
	private Collection<TaskDto> tasks;

}
