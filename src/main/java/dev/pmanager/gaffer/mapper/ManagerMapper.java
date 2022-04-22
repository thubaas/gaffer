package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.ManagerDto;
import dev.pmanager.gaffer.model.Manager;

@Mapper(componentModel = "spring")
public abstract class ManagerMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "managerDto.name")
	@Mapping(target = "surname", source = "managerDto.surname")
	@Mapping(target = "avatarUrl", source = "managerDto.avatarUrl")
	@Mapping(target = "account", source = "managerDto.account")
	@Mapping(target = "project", source = "managerDto.project")
	public abstract Manager map(ManagerDto managerDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "manager.name")
	@Mapping(target = "surname", source = "manager.surname")
	@Mapping(target = "avatarUrl", source = "manager.avatarUrl")
	@Mapping(target = "account", source = "manager.account")
	@Mapping(target = "project", source = "manager.project")
	public abstract ManagerDto map(Manager manager);

}
