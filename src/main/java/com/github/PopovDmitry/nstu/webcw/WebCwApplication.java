package com.github.PopovDmitry.nstu.webcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
//@ComponentScan({"com.github.PopovDmitry.nstu.webcw.repository"})
//@ComponentScan({"com.github.PopovDmitry.nstu.webcw"})
//@EnableJpaRepositories("com.github.PopovDmitry.nstu.webcw.repository")
public class WebCwApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCwApplication.class, args);
	}

}
