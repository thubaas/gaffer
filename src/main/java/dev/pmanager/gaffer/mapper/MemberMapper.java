package dev.pmanager.gaffer.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.enums.Role;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Leader;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;
import dev.pmanager.gaffer.repository.LeaderRepository;
import dev.pmanager.gaffer.repository.ProjectRepository;
import dev.pmanager.gaffer.repository.TeamRepository;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private LeaderRepository leaderRepository;
	@Autowired
	private ComplexTaskMapper complexTaskMapper;
	
	@Mapping(target = "id", source = "memberDto.id")
	@Mapping(target = "username", source = "memberDto.username")
	@Mapping(target = "avatar", source = "memberDto.avatar")
	@Mapping(target = "leader", expression = "java(getLeader(memberDto.getLeaderId()))")
	@Mapping(target = "project", expression = "java(getProject(memberDto.getProjectId()))")
	@Mapping(target = "team", expression = "java(getTeam(memberDto.getTeamId()))")
	@Mapping(target = "tasks", ignore = true)
	@Mapping(target = "roles", expression = "java(createRoles())")
	public abstract Member map(MemberDto memberDto);
	
	@Mapping(target = "id", source = "member.id")
	@Mapping(target = "email", source = "member.email")
	@Mapping(target = "username", source = "member.username")
	@Mapping(target = "password", source = "member.username")
	@Mapping(target = "avatar", source = "member.avatar")
	@Mapping(target = "roles", expression = "java(convertRoles(member.getRoles()))")
	@Mapping(target = "leaderId", expression = "java(member.getLeader().getId())")
	@Mapping(target = "projectId", expression = "java(member.getProject().getId())")
	@Mapping(target = "teamId", expression = "java(member.getTeam().getId())")
	@Mapping(target = "tasks", expression = "java(mapTasks(member.getTasks()))")
	public abstract MemberDto map(Member member);
	
	protected Collection<Role> createRoles() {
		return List.of(Role.MEMBER);
	}
	
	protected Collection<String> convertRoles(Collection<Role> collection) {
		return collection
				.stream()
				.map(role -> role.name())
				.collect(Collectors.toList());
	}
	
	protected Project getProject(String projectId) {
		return projectRepository.findById(projectId).get();
	}
	
	protected Team getTeam(String teamId) {
		return teamRepository.findById(teamId).get();
	}
	
	protected Leader getLeader(String leaderId) {
		return leaderRepository.findById(leaderId).get();
	}
	
	protected Collection<ComplexTaskDto> mapTasks(Collection<ComplexTask> tasks) {
		return tasks
				.stream()
				.map(task -> complexTaskMapper.map(task))
				.collect(Collectors.toList());
	}

}
