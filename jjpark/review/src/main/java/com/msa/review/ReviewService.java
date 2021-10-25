package com.msa.review;

//import com.example.productreview.product.Product;
//import com.example.productreview.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductProvider productProvider;


    public Review create(Long productId, Review review) {
        review.setProductId(productId);
        Review reviewProduct = reviewRepository.save(review);

        reviewProduct.setProduct(productProvider.getProduct(productId));
        return reviewProduct;
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviews(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}
