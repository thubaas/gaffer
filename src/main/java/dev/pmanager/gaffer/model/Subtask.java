package dev.pmanager.gaffer.model;

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
public class Subtask extends Task {
	@DBRef
	private ComplexTask parent;

	@Override
	public String toString() {
		return super.toString() + "Subtask [parent=" + parent + "]";
	}
	
	
}
