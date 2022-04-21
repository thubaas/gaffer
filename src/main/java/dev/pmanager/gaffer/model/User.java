package dev.pmanager.gaffer.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String id;
	private String name;
	private String surname;
	private String avatarUrl;
	private Account account;

}
