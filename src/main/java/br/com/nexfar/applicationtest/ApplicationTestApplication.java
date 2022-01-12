package br.com.nexfar.applicationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApplicationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationTestApplication.class, args);
	}

}
