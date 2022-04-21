package dev.pmanager.gaffer.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
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
	private Date startDate;
	private Date finishDate;
	private Status status;
	private Manager manager;
	private Leader leader;
	private Collection<Task> tasks = new HashSet<Task>();
	
	

}
