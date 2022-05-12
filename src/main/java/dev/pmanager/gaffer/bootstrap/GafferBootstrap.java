package dev.pmanager.gaffer.bootstrap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dev.pmanager.gaffer.dto.UserDto;
import dev.pmanager.gaffer.notification.EmailSender;
import dev.pmanager.gaffer.notification.MailModel;
import dev.pmanager.gaffer.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GafferBootstrap implements ApplicationListener<ContextRefreshedEvent>  {
	
	private AuthService authService;
	private EmailSender emailSender;
	private MailModel mailModel;
	

	public GafferBootstrap(AuthService authService, EmailSender emailSender, MailModel mailModel) {
		this.authService = authService;
		this.emailSender = emailSender;
		this.mailModel = mailModel;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
//			loadAccounts();
			log.info("Loaded bootstrap data");

//			try {
//				sendMail();
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
	}
	
	private void sendMail() throws AddressException, MessagingException {
		mailModel.setTo("thubelihlesibanda716@gmail.com");
		mailModel.setSubject("TEST EMAIL");
		mailModel.setBody("This is an email testing using Spring Boot");
		emailSender.sendMail(mailModel);
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
