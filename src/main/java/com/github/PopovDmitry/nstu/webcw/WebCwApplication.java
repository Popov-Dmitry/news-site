package com.github.PopovDmitry.nstu.webcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class WebCwApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCwApplication.class, args);
	}

}
