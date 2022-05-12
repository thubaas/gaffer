package dev.pmanager.gaffer.dto;

import java.time.Instant;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@NoArgsConstructor
public class RefreshTokenDto {
	
	private String id;
	private String refreshToken;
	private String username;
	private Instant expiryDate;

}
