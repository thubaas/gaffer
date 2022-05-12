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
	
	private String leaderId;
	private String teamId;
	private String projectId;
	private Collection<ComplexTaskDto> tasks;

}
