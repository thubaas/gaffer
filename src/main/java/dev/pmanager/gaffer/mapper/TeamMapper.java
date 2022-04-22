package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.TeamDto;
import dev.pmanager.gaffer.model.Team;

@Mapper(componentModel = "spring")
public abstract class TeamMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "teamDto.name")
	@Mapping(target = "members", source = "teamDto.members")
	@Mapping(target = "leader", source = "teamDto.leader")
	public abstract Team map(TeamDto teamDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "team.name")
	@Mapping(target = "members", source = "team.members")
	@Mapping(target = "leader", source = "team.leader")
	public abstract TeamDto map(Team team);
	
	

}
