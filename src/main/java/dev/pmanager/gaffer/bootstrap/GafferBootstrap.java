package dev.pmanager.gaffer.bootstrap;


import java.time.LocalDate;
import java.util.Date;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dev.pmanager.gaffer.dto.UserDto;
import dev.pmanager.gaffer.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GafferBootstrap implements ApplicationListener<ContextRefreshedEvent>  {
	
	private AuthService authService;
	
	public GafferBootstrap(AuthService authService) {
		this.authService = authService;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
//			loadAccounts();
			log.info("Loaded bootstrap data");
			log.info("Today Date : {}", LocalDate.now());
			
		
	}
	
	private void loadAccounts() {
		
		UserDto acc1 = new UserDto();
		acc1.setEmail("test@test.com");
		acc1.setPassword("password");
		acc1.getRoles().add("MANAGER");
		
		UserDto acc2 = new UserDto();
		acc2.setEmail("test1@test.com");
		acc2.setPassword("password");
		acc2.getRoles().add("LEADER");
		
		UserDto acc3 = new UserDto();
		acc3.setEmail("test2@test.com");
		acc3.setPassword("password");
		acc3.getRoles().add("MEMBER");
		
		authService.signup(acc1);
		authService.signup(acc2);
		authService.signup(acc3);
	}

}
