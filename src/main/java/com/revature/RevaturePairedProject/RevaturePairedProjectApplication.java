package com.revature.RevaturePairedProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.models") //This is telling Spring to look for DB entities here
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.daos")
public class RevaturePairedProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevaturePairedProjectApplication.class, args);
	}

}
