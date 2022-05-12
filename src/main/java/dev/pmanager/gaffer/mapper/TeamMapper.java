package dev.pmanager.gaffer.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.dto.TeamDto;
import dev.pmanager.gaffer.model.Leader;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;
import dev.pmanager.gaffer.repository.LeaderRepository;
import dev.pmanager.gaffer.repository.ProjectRepository;

@Mapper(componentModel = "spring")
public abstract class TeamMapper {
	
	@Autowired
	private LeaderRepository leaderRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private MemberMapper memberMapper;
	
	@Mapping(target = "id", source = "teamDto.id")
	@Mapping(target = "name", source = "teamDto.name")
	@Mapping(target = "members", ignore = true)
	@Mapping(target = "leader", expression = "java(getLeader(teamDto.getLeaderId()))")
	@Mapping(target = "project", expression = "java(getProject(teamDto.getProjectId()))")
	public abstract Team map(TeamDto teamDto);
	
	@Mapping(target = "id", source = "team.id")
	@Mapping(target = "name", source = "team.name")
	@Mapping(target = "members", expression = "java(mapMembers(team.getMembers()))")
	@Mapping(target = "leaderId", expression = "java(team.getLeader().getId())")
	@Mapping(target = "projectId", expression = "java(team.getProject().getId())")
	public abstract TeamDto map(Team team);
	
	protected Leader getLeader(String leaderId) {
		return leaderRepository.findById(leaderId).get();
	}
	
	protected Project getProject(String projectId) {
		return projectRepository.findById(projectId).get();
	}
	
	protected Collection<MemberDto> mapMembers(Collection<Member> members) {
		return members
				.stream()
				.map(member -> memberMapper.map(member))
				.collect(Collectors.toList());
	}
	
	

}
