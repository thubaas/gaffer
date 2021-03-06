package dev.pmanager.gaffer.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
	private LocalDate startDate;
	private LocalDate finishDate;
	@DBRef
	private Member assignee;
	private Priority priority;
	private Task precedent;
	private Status status;
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", assignee=" + assignee + ", priority=" + priority + ", precedent="
				+ precedent + ", status=" + status + "]";
	}
	
	
	

}
