package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.entity.Cart;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.request.AddItemRequest;
import com.myProject.ECommerce.response.ApiResponse;
import com.myProject.ECommerce.service.CartService;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addingToCart(@RequestBody AddItemRequest request,@RequestHeader("Authorization")String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), request);
        ApiResponse response = ApiResponse.builder()
                .message("Item added to cart")
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
