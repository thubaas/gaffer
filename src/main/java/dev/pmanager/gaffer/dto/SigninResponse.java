package dev.pmanager.gaffer.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SigninResponse {
	
	private String id;
	private String email;
	private Byte[] avatar;
	private List<String> roles = new ArrayList<>();
	private String accessToken;
	private String refreshToken;
	private String username;
	private Long accessTokenExpiryMs;
	private Long refreshTokenExpiryMS;
	
	
	@Override
	public String toString() {
		return "SigninResponse [id=" + id 
				+ ", email=" + email 
				+ ", username=" + username 
				+ ", avatar=" + Arrays.toString(avatar) 
				+ ", roles=" + roles 
				+ ", accessToken=" + accessToken 
				+ ", refreshToken=" + refreshToken
				+ ", accessTokenExpiryMs=" + accessTokenExpiryMs
				+ ", refreshTokenExpiryMS=" + refreshTokenExpiryMS
				+ "]";
	}
	
	
}