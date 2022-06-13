package com.tavares.httprequests.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("sku")
    private String sku;

    public Product(String name, String brand, Double price, String sku) {
        super();
        id = UUID.randomUUID().toString();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sku = sku;
    }
}
