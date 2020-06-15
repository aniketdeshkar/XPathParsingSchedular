package com.ad.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.ad.schedular","com.ad.service"})
@EntityScan("com.ad.entity")
@EnableJpaRepositories("com.ad.repository")
public class XPathParsingSchedularApplication {

	public static void main(String[] args) {
		SpringApplication.run(XPathParsingSchedularApplication.class, args);
	}

}
