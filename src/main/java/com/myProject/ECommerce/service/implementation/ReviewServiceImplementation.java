package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.entity.Review;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.repository.ProductRepository;
import com.myProject.ECommerce.repository.ReviewRepository;
import com.myProject.ECommerce.request.ReviewRequest;
import com.myProject.ECommerce.service.ProductService;
import com.myProject.ECommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Review review = Review.builder()
                .review(request.getReview())
                .product(product)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductReviews(productId);
    }
}
