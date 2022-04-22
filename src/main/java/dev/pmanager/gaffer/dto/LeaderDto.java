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
public class LeaderDto extends UserDto {
	
	private ManagerDto manager;
	private ProjectDto project;
	private TeamDto team;
	private Collection<TaskDto> tasks;

}
