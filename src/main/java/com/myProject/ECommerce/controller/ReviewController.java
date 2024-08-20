package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.entity.Review;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.request.ReviewRequest;
import com.myProject.ECommerce.service.ReviewService;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(request, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) throws UserException, ProductException{
        List<Review> reviews = reviewService.getAllReviews(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
