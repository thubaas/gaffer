package dev.pmanager.gaffer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.pmanager.gaffer.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
	
	@Id
	private String id;
	private String email;
	private String password;
	private String username;
	private Collection<Role> roles = new ArrayList<>();
	private Byte[] avatar;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", username=" + username + ", roles="
				+ roles + ", avatar=" + Arrays.toString(avatar) + "]";
	}
	
	
	
}
