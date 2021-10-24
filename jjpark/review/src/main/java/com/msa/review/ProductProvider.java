package com.msa.review;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductProvider {

    private static final String reviewUrl = "http://localhost:8090";
    private static final String path = "/products/%s/exceptReview";

    private final RestTemplate restTemplate = new RestTemplate();

    public Product getProduct(Long productId) {

        ResponseEntity<Product> response = restTemplate.exchange(
                reviewUrl + String.format(path, productId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

}
