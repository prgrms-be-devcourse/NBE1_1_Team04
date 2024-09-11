package com.devcoruse.nbe1_1_team04.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.devcoruse.nbe1_1_team04")
@EnableJpaAuditing
public class JpaConfig {

}
