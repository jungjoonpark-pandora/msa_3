package com.msa.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewProvider reviewProvider;


    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .peek(product
                        -> product.setReviews(reviewProvider.getReviews(product.getId()))
                ).collect(Collectors.toList());
    }

    public Product findById(Long id, boolean withReview) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        if (withReview) {
            product.setReviews(reviewProvider.getReviews(product.getId()));
        }
        return product;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product newProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        return productRepository.save(product);
    }
}
