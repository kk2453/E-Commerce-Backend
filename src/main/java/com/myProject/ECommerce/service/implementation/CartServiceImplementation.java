package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Cart;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.repository.CartRepository;
import com.myProject.ECommerce.request.AddItemRequest;
import com.myProject.ECommerce.service.CartItemService;
import com.myProject.ECommerce.service.CartService;
import com.myProject.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        return "Item added to cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
