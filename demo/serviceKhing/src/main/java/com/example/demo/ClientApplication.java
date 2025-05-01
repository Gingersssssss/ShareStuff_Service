package com.example.demo.client;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    @Bean
	public CommandLineRunner get(RestTemplate restTemplate) throws Exception {
		// return args -> {
		// 	// use RestTemplate to get all handytools
		// 	ResponseEntity<List<Storage>> responseEntity = restTemplate.exchange(
		// 		"http://localhost:8080/handytools", HttpMethod.GET, 
		// 			null, new ParameterizedTypeReference<List<Storage>>() {});
		// 	List<Storage> handytools = responseEntity.getBody();
		// 	MediaType contentType = responseEntity.getHeaders().getContentType();
		// 	log.info("content-type: "+contentType);
		// 	for (Storage item : handytools) {
		// 		log.info("handytools: "+item.toString());
		// 	}
		// };

		return args -> {
			// traverse to _links (hypermedia style)
			String baseUrl = "http://localhost:8080/handytools/2";
			Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON); 
			TraversalBuilder tb = traverson.follow("handytools"); 		// <-- GET ALL ITEMS
			//"$._embedded.storageList"
			CollectionModel<EntityModel<Storage>> tools = tb.toObject(new ParameterizedTypeReference<CollectionModel<EntityModel<Storage>>>() {});

            // ResponseEntity<Storage> tools = tb.toEntity(Storage.class);
			log.info("return "+tools);
			// List<Storage> tools = tb.toObject(new ParameterizedTypeReference<List<Storage>>() {});
			for (EntityModel<Storage> item : tools) {
				log.info("handytools: "+item.toString());
			}

			// traverse to _links (for self!)
			tb = traverson.follow("self");
			Storage storage = tb.toObject(Storage.class);
			log.info("storage item = "+storage);
		};
	}

}
