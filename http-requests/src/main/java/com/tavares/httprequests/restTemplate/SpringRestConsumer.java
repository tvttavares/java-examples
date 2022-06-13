package com.tavares.httprequests.restTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavares.httprequests.domain.Product;
import com.tavares.httprequests.restTemplate.exception.RestServiceException;
import com.tavares.httprequests.restTemplate.exception.handler.CustomErrorHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SpringRestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    public void getProductAsJson() {
        String resourceUrl = "http://localhost:8080/products";

        // Fetch JSON response as String wrapped in ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class, headers);
        String productsJson = response.getBody();
        System.out.println(productsJson);
    }

    public void getProductAsGetForEntity() {
        String resourceUrl = "http://localhost:8080/products";

        // Fetch response as List wrapped in ResponseEntity
        ResponseEntity<List> response = restTemplate.getForEntity(resourceUrl, List.class);
        List<Product> products = response.getBody();
        System.out.println(products);
    }

    public void getProductObjects() {
        String resourceUrl = "http://localhost:8080/products";

        // Fetching response as Object
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<?> products = restTemplate.getForObject(resourceUrl, List.class);
        System.out.println(products);
    }

    public void getSingleProductAsEntity() {
        String resourceUrl = "http://localhost:8080/products/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        // Fetching response as Object
        Product product = restTemplate.getForObject(resourceUrl, Product.class, params);
        System.out.println(product);
    }

    public void createProduct() {
        String resourceUrl = "http://localhost:8080/products";

        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request = new HttpEntity<Product>(
                new Product("Television", "Samsung", 1145.67, "S001"));

        // Send the request body in HttpEntity for HTTP POST request
        String productCreateResponse = restTemplate.postForObject(resourceUrl, request, String.class);

        System.out.println(productCreateResponse);
    }

    public void createProductFullResponse() {
        String resourceUrl = "http://localhost:8080/productsFullResponse";

        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request = new HttpEntity<Product>(
                new Product("Television", "Samsung", 1145.67, "S001"));

        // Send the request body in HttpEntity for HTTP POST request
        Product productCreateResponse = restTemplate.postForObject(resourceUrl, request, Product.class);
        System.out.println(productCreateResponse);
    }

    public void createProductWithExchange() {
        String resourceUrl = "http://localhost:8080/productsFullResponse";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request =
                new HttpEntity<>(new Product("Television", "Samsung", 1145.67, "S001"), headers);

        ResponseEntity<Product> productCreateResponse =
                restTemplate.exchange(resourceUrl, HttpMethod.POST, request, Product.class);

        System.out.println(productCreateResponse);
    }

    public void updateProductWithExchange() {
        String resourceUrl = "http://localhost:8080/products";

        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request = new HttpEntity<Product>(
                new Product("Television", "Samsung", 1145.67, "S001"));

        // Send the PUT method as a method parameter
        restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, Void.class);
    }

    public void getProductAsStream() {
        final Product fetchProductRequest = new Product("Television", "Samsung", 1145.67, "S001");
//        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8080/products";

        RequestCallback requestCallback = request -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(request.getBody(),
                    fetchProductRequest);

            request.getHeaders()
                    .setAccept(Arrays.asList(
                            MediaType.APPLICATION_OCTET_STREAM,
                            MediaType.ALL));
        };

        ResponseExtractor<Void> responseExtractor = response -> {
//            Path path = Paths.get("some/path");
//            Files.copy(response.getBody(), path);
            return null;
        };
        restTemplate.execute(resourceUrl,
                HttpMethod.GET,
                requestCallback,
                responseExtractor);
    }

    public void submitProductForm() {
        String resourceUrl = "http://localhost:8080/products";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the form inputs in a multivaluemap
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("sku", "S34455");
        map.add("name", "Television");
        map.add("brand", "Samsung");

        // Create the request body by wrapping
        // the MultiValueMap in HttpEntity
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, request, String.class);
        System.out.println(response.getBody());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int connectTimeout = 5000;
        int readTimeout = 5000;

        // Create an instance of Apache HttpClient
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }

    public void createProductWithLocation() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        String resourceUrl = "http://localhost:8080/products";
        HttpEntity<Product> request = new HttpEntity<>(
                new Product("Television", "Samsung", 1145.67, "S001"));
        URI location = restTemplate.postForLocation(resourceUrl, request);

        System.out.println(location);
    }

    public void getAllowedOps() {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:8080/products";
        Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(resourceUrl);
        HttpMethod[] supportedMethods = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
        System.out.println(optionsForAllow);
    }

    public void getProductWithError() {
        RestTemplate restTemplate2;

        RestTemplateCustomizer customizers = new RestTemplateCustomizer() {
            @Override
            public void customize(RestTemplate s) {
                s.setErrorHandler(new CustomErrorHandler());
            }
        };
        RestTemplateBuilder builder = new RestTemplateBuilder(customizers);
        restTemplate2 = builder.build();

        System.out.println("Default error handler::" + restTemplate2.getErrorHandler());
        String resourceUrl = "http://localhost:8080/product/error";
        try {
            Product product = restTemplate2.getForObject(resourceUrl, Product.class);

        } catch (RestServiceException ex) {
            System.out.println("error occured: [" + ex.getError() + "] in service:: " + ex.getServiceName());
        }

    }

}
