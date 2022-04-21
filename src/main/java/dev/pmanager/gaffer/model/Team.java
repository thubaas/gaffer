package dev.pmanager.gaffer.model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
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
	private Collection<User> members = new HashSet<>();
	private Leader leader;

}
