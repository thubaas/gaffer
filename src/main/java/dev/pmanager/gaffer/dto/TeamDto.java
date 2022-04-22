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
public class TeamDto {
	
	private String id;
	private String name;
	private Collection<UserDto> members;
	private LeaderDto leader;

}
