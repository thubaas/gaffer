package dev.pmanager.gaffer.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Team {
	
	@Id
	private String id;
	private String name;
	@DBRef
	private Project project;
	@DBRef
	private Collection<Member> members = new ArrayList<>();
	@DBRef
	private Leader leader;
	
	@Override
	public String toString() {
		return "Team [id=" + id 
				+ ", name=" + name 
				+ ", project=" + project 
				+ ", leader=" + leader + "]";
	}
	
	
	

}
