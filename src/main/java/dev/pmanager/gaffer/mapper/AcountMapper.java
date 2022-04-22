package dev.pmanager.gaffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.pmanager.gaffer.dto.AccountDto;
import dev.pmanager.gaffer.model.Account;

@Mapper(componentModel = "spring")
public abstract class AcountMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", source = "accountDto.email")
	@Mapping(target = "password", source = "accountDto.password")
	@Mapping(target = "role", source = "accountDto.role")
	public abstract Account map(AccountDto accountDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", source = "account.email")
	@Mapping(target = "password", source = "account.password")
	@Mapping(target = "role", source = "account.role")
	public abstract AccountDto map(Account account);

}
