package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.entity.CartItem;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.CartItemException;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.response.ApiResponse;
import com.myProject.ECommerce.service.CartItemService;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws UserException, CartItemException{
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);
        ApiResponse response = ApiResponse.builder()
                .message("Item deleted from cart")
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws UserException, CartItemException{
        User user = userService.findUserProfileByJwt(jwt);
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }
}
