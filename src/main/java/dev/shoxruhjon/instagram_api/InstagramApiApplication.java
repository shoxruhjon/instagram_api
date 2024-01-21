package dev.shoxruhjon.instagram_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InstagramApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramApiApplication.class, args);
	}

}
