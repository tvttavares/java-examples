package com.tavares.httprequests;

import com.tavares.httprequests.restTemplate.SpringRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpRequestsApplication implements CommandLineRunner {

    @Autowired
    private SpringRestConsumer restConsumer;

    public static void main(String[] args) {
        SpringApplication.run(HttpRequestsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        /** spring restTemplate calls */
//		restConsumer.getProductAsJson();
//		restConsumer.getProductAsGetForEntity();
//		restConsumer.getProductObjects();
//		restConsumer.getSingleProductAsEntity();
//		restConsumer.createProduct();
//		restConsumer.createProductFullResponse();
//		restConsumer.createProductWithExchange();
//		restConsumer.getProductAsStream();
//		restConsumer.submitProductForm();
//		restConsumer.createProductWithLocation();
//		restConsumer.getAllowedOps();
//      restConsumer.getProductWithError();
    }
}
