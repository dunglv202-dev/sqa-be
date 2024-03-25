package vn.edu.ptit.sqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SqaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqaApplication.class, args);
	}

}
