package dev.pmanager.gaffer.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	@Id
	private String id;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private Member assignee;
	private Priority priority;
	private Task precedent;
	private Status status;
	

}
