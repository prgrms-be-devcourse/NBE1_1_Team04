package com.grepp.nbe1_1_team04_mw_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Nbe11Team04Mw1Application {

	public static void main(String[] args) {
		SpringApplication.run(Nbe11Team04Mw1Application.class, args);
	}

}
