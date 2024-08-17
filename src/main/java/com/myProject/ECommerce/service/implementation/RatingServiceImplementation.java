package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.entity.Rating;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.repository.RatingRepository;
import com.myProject.ECommerce.request.RatingRequest;
import com.myProject.ECommerce.service.ProductService;
import com.myProject.ECommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Rating rating = Rating.builder()
                .user(user)
                .product(product)
                .rating(request.getRating())
                .createAt(LocalDateTime.now())
                .build();
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {
        return ratingRepository.getAllProductRatings(productId);
    }
}
