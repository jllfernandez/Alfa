package com.luke.bie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class LukeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LukeWebApplication.class);
	}

}
