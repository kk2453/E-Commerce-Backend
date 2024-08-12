package com.myProject.ECommerce.service;

import com.myProject.ECommerce.entity.Cart;
import com.myProject.ECommerce.entity.CartItem;
import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.CartItemException;
import com.myProject.ECommerce.exception.UserException;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public String removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
