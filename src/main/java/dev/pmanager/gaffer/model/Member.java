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
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Member extends User{
	
	private Leader leader;
	private Team team;
	private Project project;
	private Collection<ComplexTask> tasks = new ArrayList<>();

}
