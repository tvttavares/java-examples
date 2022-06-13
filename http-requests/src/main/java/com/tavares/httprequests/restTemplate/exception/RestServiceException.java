package com.tavares.httprequests.restTemplate.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RestServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String serviceName;
    private HttpStatus statusCode;
    private String error;
}