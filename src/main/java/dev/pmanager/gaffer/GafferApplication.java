package dev.pmanager.gaffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GafferApplication {

	public static void main(String[] args) {
		SpringApplication.run(GafferApplication.class, args);
	}

}
