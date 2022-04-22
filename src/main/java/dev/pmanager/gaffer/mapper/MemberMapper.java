package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.model.Member;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "memberDto.name")
	@Mapping(target = "surname", source = "memberDto.surname")
	@Mapping(target = "avatarUrl", source = "memberDto.avatarUrl")
	@Mapping(target = "account", source = "memberDto.account")
	@Mapping(target = "leader", source = "memberDto.leader")
	@Mapping(target = "project", source = "memberDto.project")
	@Mapping(target = "team", source = "memberDto.team")
	@Mapping(target = "tasks", source = "memberDto.tasks")
	public abstract Member map(MemberDto memberDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "member.name")
	@Mapping(target = "surname", source = "member.surname")
	@Mapping(target = "avatarUrl", source = "member.avatarUrl")
	@Mapping(target = "account", source = "member.account")
	@Mapping(target = "leader", source = "member.leader")
	@Mapping(target = "project", source = "member.project")
	@Mapping(target = "team", source = "member.team")
	@Mapping(target = "tasks", source = "member.tasks")
	public abstract MemberDto map(Member member);

}
