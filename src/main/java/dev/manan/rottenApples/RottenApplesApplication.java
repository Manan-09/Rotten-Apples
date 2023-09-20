package dev.manan.rottenApples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RottenApplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RottenApplesApplication.class, args);
	}

}
