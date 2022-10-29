package io.katkov.spring_boot_jpa_relations_demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.katkov.spring_boot_jpa_relations_demo.entity")
@EnableJpaRepositories("io.katkov.spring_boot_jpa_relations_demo.repository")
@EnableTransactionManagement
public class DomainConfig {
}
