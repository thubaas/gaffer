package dev.pmanager.gaffer.dto;

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
	private String name;
	private String surname;
	private String avatarUrl;
	private AccountDto account;

}
