package dev.pmanager.gaffer.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev.pmanager.gaffer.dto.ManagerDto;
import dev.pmanager.gaffer.enums.Role;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.repository.ProjectRepository;

@Mapper(componentModel = "spring")
public abstract class ManagerMapper {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Mapping(target = "id", source = "managerDto.id")
	@Mapping(target = "username", source = "managerDto.username")
	@Mapping(target = "avatar", source = "managerDto.avatar")
	@Mapping(target = "project", expression = "java(getProject(managerDto.getProjectId()))")
	@Mapping(target = "roles", expression = "java(createRoles())")
	public abstract Manager map(ManagerDto managerDto);
	
	@Mapping(target = "id", source = "manager.id")
	@Mapping(target = "email", source = "manager.email")
	@Mapping(target = "username", source = "manager.username")
	@Mapping(target = "avatar", source = "manager.avatar")
	@Mapping(target = "roles", expression = "java(convertRoles(manager.getRoles()))")
	@Mapping(target = "projectId", expression = "java(manager.getProject().getId())")
	public abstract ManagerDto map(Manager manager);
	
	protected Collection<Role> createRoles() {
		return List.of(Role.MEMBER);
	}
	
	protected Collection<String> convertRoles(Collection<Role> roles) {
		return roles
				.stream()
				.map(role -> role.name())
				.collect(Collectors.toList());
	}
	
	protected Project getProject(String projectId) {
		return projectRepository.findById(projectId).get();
	}

}
