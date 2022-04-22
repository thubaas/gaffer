package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.LeaderDto;
import dev.pmanager.gaffer.model.Leader;

@Mapper(componentModel = "spring")
public abstract class LeaderMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "leaderDto.name")
	@Mapping(target = "surname", source = "leaderDto.surname")
	@Mapping(target = "avatarUrl", source = "leaderDto.avatarUrl")
	@Mapping(target = "account", source = "leaderDto.account")
	@Mapping(target = "manager", source = "leaderDto.manager")
	@Mapping(target = "project", source = "leaderDto.project")
	@Mapping(target = "team", source = "leaderDto.team")
	@Mapping(target = "tasks", source = "leaderDto.tasks")
	public abstract Leader map(LeaderDto leaderDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "leader.name")
	@Mapping(target = "surname", source = "leader.surname")
	@Mapping(target = "avatarUrl", source = "leader.avatarUrl")
	@Mapping(target = "account", source = "leader.account")
	@Mapping(target = "manager", source = "leader.manager")
	@Mapping(target = "project", source = "leader.project")
	@Mapping(target = "team", source = "leader.team")
	@Mapping(target = "tasks", source = "leader.tasks")
	public abstract LeaderDto map(Leader leader);
	
	

}
