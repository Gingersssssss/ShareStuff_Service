package com.example.demo;

import com.example.demo.model.Storage;
import com.example.demo.repository.StorageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StorageRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Storage("hammer", "kt", "outside storage", false)));
            log.info("Preloading " + repository.save(new Storage("screwdriver", "kt", "toolbox 2nd floor", false)));
            log.info("Preloading " + repository.save(new Storage("paint brush", "kt", "paint cabinet", false)));
        };
    }
}
