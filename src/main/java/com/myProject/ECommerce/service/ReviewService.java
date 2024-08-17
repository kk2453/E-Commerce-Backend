package com.myProject.ECommerce.service;


import com.myProject.ECommerce.entity.Review;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest request, User user) throws ProductException;
    public List<Review> getAllReviews(Long productId);
}