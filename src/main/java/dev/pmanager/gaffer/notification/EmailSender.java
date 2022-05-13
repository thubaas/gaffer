package dev.pmanager.gaffer.notification;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class EmailSender {
	
	private final JavaMailSender mailSender;
	
	@Async
	public void sendMail(MailModel mailModel) throws AddressException, MessagingException {
		log.info("Sending Simple Mail : {}", mailModel);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mailModel.getTo());
		msg.setFrom(mailModel.getFROM());
		msg.setSubject(mailModel.getSubject());
		msg.setText(mailModel.getBody());
		mailSender.send(msg);
		log.info("Sent Simple Mail");
	}

}
