package dev.pmanager.gaffer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@SpringBootApplication
@EnableAsync
public class GafferApplication {

	public static void main(String[] args) {
		SpringApplication.run(GafferApplication.class, args);
	}
	
	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
	    GoogleCredentials googleCredentials = GoogleCredentials
	            .fromStream(new ClassPathResource("gaffer-pm-service-key.json").getInputStream());
	    FirebaseOptions firebaseOptions = FirebaseOptions
	            .builder()
	            .setCredentials(googleCredentials)
	            .build();
	    FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "gaffer-pm");
	    return FirebaseMessaging.getInstance(app);
	}

}
