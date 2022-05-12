package dev.pmanager.gaffer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class VerificationToken {
	
	@Id
	private String id;
	private String token;
	private Long expirationTime;
	private User user;

}
