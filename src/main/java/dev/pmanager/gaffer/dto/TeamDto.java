package dev.pmanager.gaffer.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "Team name is required")
	private String name;
	private Collection<MemberDto> members = new ArrayList<>();
	@NotBlank(message = "Team leader is required")
	private String leaderId;
	@NotBlank(message = "Project is required")
	private String projectId;
	
	@Override
	public String toString() {
		return "TeamDto [id=" + id 
				+ ", name=" + name 
				+ ", members=" + members 
				+ ", leaderId=" + leaderId 
				+ ", projectId=" + projectId 
				+ "]";
	}
	
	

}
