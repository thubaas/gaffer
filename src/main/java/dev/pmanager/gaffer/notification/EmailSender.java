package dev.pmanager.gaffer.notification;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class EmailSender {
		
	public void sendMail(MailModel mailModel) throws AddressException, MessagingException {
		log.info("Sending Email : {}", mailModel);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(mailModel.getFROM(), mailModel.getPASSWORD());
		      }
		   });
		
		
		Message msg = new MimeMessage(session);
		
		msg.setFrom(new InternetAddress(mailModel.getFROM(), false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailModel.getTo()));
		msg.setSubject(mailModel.getSubject());
		msg.setContent(mailModel.getBody(), "text/html");
		msg.setSentDate(new Date());
		Transport.send(msg);
	}

}
