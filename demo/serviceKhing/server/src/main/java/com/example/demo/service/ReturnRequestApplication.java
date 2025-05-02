package com.example.demo.service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.BorrowedRecord;

@SpringBootApplication
public class ReturnRequestApplication {

    private static final Logger log = LoggerFactory.getLogger(ReturnRequestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReturnRequestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
/* 
    @Bean
    public CommandLineRunner get(RestTemplate restTemplate) {
        return args -> {
            
            try {
                // Base URL for the API
                String baseUrl = "http://localhost:8080/handytools";

                // Using RestTemplate to fetch all handytools
                ResponseEntity<List<Storage>> responseEntity = restTemplate.exchange(
                        baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Storage>>() {});
                List<Storage> handytools = responseEntity.getBody();

                // Log the fetched handytools
                log.info("Fetched handytools using RestTemplate:");
                for (Storage item : handytools) {
                    log.info(item.toString());
                }

                // Using Traverson to traverse hypermedia links
                Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);
                TraversalBuilder tb = traverson.follow("self"); // Follow the "self" link

                // Fetching the collection of Storage items
                CollectionModel<EntityModel<Storage>> tools = tb.toObject(
                        new ParameterizedTypeReference<CollectionModel<EntityModel<Storage>>>() {});

                log.info("Fetched handytools using Traverson:");
                for (EntityModel<Storage> item : tools) {
                    log.info(item.toString());
                }

            } catch (Exception e) {
                log.error("Error occurred while fetching handytools: ", e);
            }
        };
    }*/
}
