package dev.pmanager.gaffer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.pmanager.gaffer.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Account {
	
	@Id
	private String id;
	private String email;
	private String password;
	private Role role;

}
