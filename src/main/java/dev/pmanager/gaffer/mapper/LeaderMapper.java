package dev.pmanager.gaffer.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.ComplexTaskDto;
import dev.pmanager.gaffer.dto.LeaderDto;
import dev.pmanager.gaffer.enums.Role;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Leader;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;
import dev.pmanager.gaffer.repository.LeaderRepository;
import dev.pmanager.gaffer.repository.ManagerRepository;
import dev.pmanager.gaffer.repository.ProjectRepository;
import dev.pmanager.gaffer.repository.TeamRepository;

@Mapper(componentModel = "spring")
public abstract class LeaderMapper {
	
	@Autowired
	private LeaderRepository leaderRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private ComplexTaskMapper complexTaskMapper;
	
	@Mapping(target = "id", source = "leaderDto.id")
	@Mapping(target = "username", source = "leaderDto.username")
	@Mapping(target = "manager", expression = "java(getManager(leaderDto.getManagerId()))")
	@Mapping(target = "project", expression = "java(getProject(leaderDto.getProjectId()))")
	@Mapping(target = "team", expression = "java(getTeam(leaderDto.getTeamId()))")
	@Mapping(target = "tasks", ignore = true)
	@Mapping(target = "roles", expression = "java(createRoles())")
	public abstract Leader map(LeaderDto leaderDto);
	
	@Mapping(target = "id", source = "leader.id")
	@Mapping(target = "email", source = "leader.email")
	@Mapping(target = "username", source = "leader.username")
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "roles", expression = "java(convertRoles(leader.getRoles()))")
	@Mapping(target = "avatar", source = "leader.avatar")
	@Mapping(target = "managerId", expression = "java(leader.getManager().getId())")
	@Mapping(target = "projectId", expression = "java(leader.getProject().getId())")
	@Mapping(target = "teamId", expression = "java(leader.getTeam().getId())")
	@Mapping(target = "tasks", expression = "java(mapTasks(leader.getTasks()))")
	public abstract LeaderDto map(Leader leader);
	
	
	protected Collection<Role> createRoles() {
		return List.of(Role.LEADER);
	}
	
	protected Collection<String> convertRoles(Collection<Role> roles) {
		return roles
				.stream()
				.map(role -> role.name())
				.collect(Collectors.toList());
	}
	
	protected Collection<ComplexTaskDto> mapTasks(Collection<ComplexTask> tasks) {
		return tasks
				.stream()
				.map(task -> complexTaskMapper.map(task))
				.collect(Collectors.toList());
	}
	
	protected Leader getLeader(String leaderId) {
		return leaderRepository.findById(leaderId).get();
	}
	
	protected Manager getManager(String managerId) {
		return managerRepository.findById(managerId).get();
	}
	
	protected Project getProject(String projectId) {
		return projectRepository.findById(projectId).get();
	}
	
	protected Team getTeam(String teamId) {
		return teamRepository.findById(teamId).get();
	}
	
}
