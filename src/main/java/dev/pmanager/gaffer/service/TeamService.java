package dev.pmanager.gaffer.service;

import java.util.List;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.dto.TeamDto;

public interface TeamService {
	
	TeamDto addTeam(TeamDto teamDto);
	TeamDto getTeam(String teamId);
	List<TeamDto> getTeamsByProject(String projectId);
	boolean deleteTeam(String teamId);
	TeamDto addMember( String teamId, MemberDto memberDto);
	TeamDto removeMember(String teamId, String memberId);
	TeamDto updateTeam(TeamDto teamDto);
	

}
