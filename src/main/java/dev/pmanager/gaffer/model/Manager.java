package dev.pmanager.gaffer.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Manager extends User{
	
	private Project project;

	@Override
	public String toString() {
		return "Manager [project=" + project + "]";
	}
	
	
}
