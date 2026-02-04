package com.luke.bie.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.luke.bie" })
@ComponentScan("com.luke.bie")
@ComponentScan("com.luke.bie")
@EntityScan("com.luke.bie.entitys")
//@EntityScan("com.luke.bie.*")
@EnableJpaRepositories("com.luke.bie.repository")

public class AppConfig {

}
