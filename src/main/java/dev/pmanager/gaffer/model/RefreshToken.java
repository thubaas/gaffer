package dev.pmanager.gaffer.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class RefreshToken {
	
	@Id
	private String id;
	private User user;
	private String token;
	private Instant expiryDate;

}
