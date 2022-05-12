package dev.pmanager.gaffer.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.pmanager.gaffer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Project {
	
	@Id
	private String id;
	private String title;
	private LocalDate startDate;
	private LocalDate finishDate;
	private Status status;
	@DBRef
	private Manager manager;
	@DBRef
	private List<ComplexTask> tasks;
	
	@Override
	public String toString() {
		return "Project [id=" + id 
				+ ", title=" + title
				+ ", startDate=" + startDate 
				+ ", finishDate=" + finishDate 
				+ ", status=" + status
				+ ", manager=" + manager 
				+ ", tasks=" + tasks + "]";
	}
	
	

}
