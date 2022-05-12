package dev.pmanager.gaffer.dto;

import java.util.ArrayList;
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
	private Collection<MemberDto> members = new ArrayList<>();
	private String leaderId;
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
