package dev.pmanager.gaffer.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dev.pmanager.gaffer.dto.ProjectDto;
import dev.pmanager.gaffer.dto.UserDto;
import dev.pmanager.gaffer.service.AuthService;
import dev.pmanager.gaffer.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GafferBootstrap implements ApplicationListener<ContextRefreshedEvent>  {
	
	private AuthService authService;
	private ProjectService projectService;

	public GafferBootstrap(AuthService authService, ProjectService projectService) {
		this.authService = authService;
		this.projectService = projectService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
//		loadAccounts();
//		loadProject();



		
	}
	
	private void loadProject() {
		ProjectDto dto = new ProjectDto();
		dto.setTitle("First Project");
		dto.setStartDate("2022-05-13");
		dto.setFinishDate("2022-06-21");
		dto.setStatus("OPEN");
		dto.setManagerId("627e61e20f93e23109fbd0bb");
		projectService.addProject(dto);
		
	}
	
	private void loadAccounts() {
		log.info("Loading Accounts");
		UserDto acc1 = new UserDto();
		acc1.setEmail("test1@test.com");
		acc1.setPassword("password");
		acc1.getRoles().add("MANAGER");
		
		UserDto acc2 = new UserDto();
		acc2.setEmail("test2@test.com");
		acc2.setPassword("password");
		acc2.getRoles().add("LEADER");
		
		UserDto acc3 = new UserDto();
		acc3.setEmail("test3@test.com");
		acc3.setPassword("password");
		acc3.getRoles().add("MEMBER");
		
		UserDto acc4 = new UserDto();
		acc4.setEmail("test4@test.com");
		acc4.setPassword("password");
		acc4.getRoles().add("MANAGER");
		
		UserDto acc5 = new UserDto();
		acc5.setEmail("test5@test.com");
		acc5.setPassword("password");
		acc5.getRoles().add("LEADER");
		
		UserDto acc6 = new UserDto();
		acc6.setEmail("test6@test.com");
		acc6.setPassword("password");
		acc6.getRoles().add("MEMBER");
		
		UserDto acc7 = new UserDto();
		acc7.setEmail("test7@test.com");
		acc7.setPassword("password");
		acc7.getRoles().add("MEMBER");
		
		UserDto acc8 = new UserDto();
		acc8.setEmail("test8@test.com");
		acc8.setPassword("password");
		acc8.getRoles().add("MEMBER");
		
		UserDto acc9 = new UserDto();
		acc9.setEmail("test9@test.com");
		acc9.setPassword("password");
		acc9.getRoles().add("MEMBER");
		
		UserDto acc10 = new UserDto();
		acc10.setEmail("test10@test.com");
		acc10.setPassword("password");
		acc10.getRoles().add("MEMBER");
		
		UserDto acc11 = new UserDto();
		acc11.setEmail("test11@test.com");
		acc11.setPassword("password");
		acc11.getRoles().add("MEMBER");
		
		UserDto acc12 = new UserDto();
		acc12.setEmail("test12@test.com");
		acc12.setPassword("password");
		acc12.getRoles().add("MEMBER");
		
		authService.signup(acc1);
		authService.signup(acc2);
		authService.signup(acc3);
		authService.signup(acc4);
		authService.signup(acc5);
		authService.signup(acc6);
		authService.signup(acc7);
		authService.signup(acc8);
		authService.signup(acc9);
		authService.signup(acc10);
		authService.signup(acc11);
		authService.signup(acc12);
		
		log.info("Loaded Accounts");
	}

}
