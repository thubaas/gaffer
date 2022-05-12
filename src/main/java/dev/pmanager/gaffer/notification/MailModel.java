package dev.pmanager.gaffer.notification;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class MailModel {
	
	private final String FROM = "thubaprof@gmail.com";
	private final String PASSWORD = "Or10N!!!";
	private String to;
	private String subject;
	private String body;
	
	@Override
	public String toString() {
		return "Mail [from=" + FROM 
				+ ", to=" + to 
				+ ", subject=" + subject 
				+ ", body=" + body + "]";
	}
	
}
