package com.tavares.httprequests.restTemplate.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestTemplateError {

    private String timestamp;
    private String status;
    private String error;
    private String path;
    private String message;

}