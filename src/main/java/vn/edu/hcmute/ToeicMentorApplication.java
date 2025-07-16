package vn.edu.hcmute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
})

//@SpringBootApplication
public class ToeicMentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToeicMentorApplication.class, args);
	}

}
