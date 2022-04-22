package dev.pmanager.gaffer.dto;

import dev.pmanager.gaffer.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {	
	private String id;
	private String email;
	private String password;
	private Role role;
}
