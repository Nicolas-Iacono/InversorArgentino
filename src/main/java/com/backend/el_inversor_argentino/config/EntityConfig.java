package com.backend.el_inversor_argentino.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.backend.el_inversor_argentino.entity")
@EnableJpaRepositories("com.backend.el_inversor_argentino.repository")
@EnableTransactionManagement
public class EntityConfig {
}
