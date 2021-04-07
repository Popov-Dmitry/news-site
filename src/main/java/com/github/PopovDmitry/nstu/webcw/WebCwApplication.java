package com.github.PopovDmitry.nstu.webcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class WebCwApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCwApplication.class, args);
	}

}
