package io.katkov.spring_boot_jpa_relations_demo.readfilefromdirectory;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReadFile implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private final ResourcePatternResolver resolver;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Resource[] resources = resolver.getResources("classpath:docs/*");
        for (Resource resource : resources) {
            log.info(resource.getFilename());
            log.info(new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
        }
    }
}
