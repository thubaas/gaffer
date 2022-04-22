package dev.pmanager.gaffer.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto extends UserDto {
	
//	private String id;
//	private String name;
//	private String surname;
//	private String avatarUrl;
//	private AccountDto accountDto;
	private LeaderDto leader;
	private TeamDto team;
	private ProjectDto project;
	private Collection<TaskDto> tasks;

}
