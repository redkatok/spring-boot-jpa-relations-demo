package io.katkov.spring_boot_jpa_relations_demo.support;


import com.github.gavlyukovskiy.boot.jdbc.decorator.DataSourceDecoratorAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@TestPropertySource(properties = "decorator.datasource.enabled=true")
@ImportAutoConfiguration(DataSourceDecoratorAutoConfiguration.class)
public @interface EnableSqlLogging {
}