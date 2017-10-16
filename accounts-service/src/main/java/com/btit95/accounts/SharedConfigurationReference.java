package com.btit95.accounts;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.common")
@EnableJpaRepositories("com.common.repositories")
@EntityScan("com.common.models")
public class SharedConfigurationReference {
	
}