package dev.pmanager.gaffer.service;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import dev.pmanager.gaffer.model.Note;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class FirebaseMessagingService {
	
	private final FirebaseMessaging firebaseMessaging;
	
	public ApiFuture<String> pushNotification(Note note, String token) throws FirebaseMessagingException {
		
		log.info("Pushing Notification : {}", note);
		
		Notification notification = Notification
				.builder()
				.setTitle(note.getSubjectl())
				.setBody(note.getContent())
				.build();
		
		Message message = Message
				.builder()
				.setToken(token)
				.setNotification(notification)
				.putAllData(note.getData())
				.build();
		return firebaseMessaging.sendAsync(message);
	}

}
