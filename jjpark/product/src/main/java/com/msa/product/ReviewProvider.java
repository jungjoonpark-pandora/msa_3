package com.msa.product;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ReviewProvider {

    private static final String reviewUrl = "http://localhost:8090";
    private static final String path = "/products/%s/reviews";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Review> getReviews(Long productId) {

        ResponseEntity<List<Review>> response = restTemplate.exchange(
                reviewUrl + String.format(path, productId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

}
