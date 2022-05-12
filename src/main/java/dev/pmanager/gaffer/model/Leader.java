package dev.pmanager.gaffer.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Leader extends User {
	
	private Manager manager;
	private Project project;
	private Team team;
	private Collection<ComplexTask> tasks = new ArrayList<>();

}
