package dev.pmanager.gaffer.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String id;
	private String email;
	private String password;
	private Collection<String> roles = new ArrayList<String>();
	private String username;
	private Byte[] avatar;
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + ", username="
				+ username + ", avatar=" + Arrays.toString(avatar) + "]";
	}
	
	

}
