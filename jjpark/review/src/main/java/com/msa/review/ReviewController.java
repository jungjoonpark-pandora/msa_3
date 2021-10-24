package com.msa.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public Review createReview(@PathVariable("productId") Long productId,
                               @RequestBody Review review) {
        return reviewService.create(productId, review);
    }

    @DeleteMapping("/{id}")
    public void createReview(@PathVariable("productId") Long productId,
                             @PathVariable("id") Long id) {
        reviewService.delete(id);
    }

    @GetMapping("")
    public List<Review> getReviews(@PathVariable("productId") Long productId) {
        return reviewService.getReviews(productId);
    }
}
