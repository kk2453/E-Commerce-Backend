package com.myProject.ECommerce.service;

import com.myProject.ECommerce.entity.Rating;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest request, User user) throws ProductException;
    public List<Rating> getProductRating(Long productId);
}
