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
	
	private String managerId;
	private String projectId;
	private String teamId;
	private Collection<ComplexTaskDto> tasks;

}
