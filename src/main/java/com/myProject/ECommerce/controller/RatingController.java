package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.entity.Rating;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.request.RatingRequest;
import com.myProject.ECommerce.service.RatingService;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(request, user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt) throws UserException,ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductRating(productId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

}
