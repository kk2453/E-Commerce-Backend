package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Cart;
import com.myProject.ECommerce.entity.CartItem;
import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.CartItemException;
import com.myProject.ECommerce.exception.UserException;
import com.myProject.ECommerce.repository.CartItemRepository;
import com.myProject.ECommerce.repository.CartRepository;
import com.myProject.ECommerce.service.CartItemService;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImplementation implements CartItemService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public CartItem createCartItem(CartItem cartItem){
        cartItem.setQuantity(1);
        cartItem.setPrice(Integer.parseInt(cartItem.getProduct().getPrice())*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item =findCartItemById(id);
        User user = userService.findUserById(item.getUserId());
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(Integer.valueOf(cartItem.getProduct().getPrice()));
            item.setDiscountedPrice(cartItem.getDiscountedPrice()*item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return null;
    }

    @Override
    public String removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        return "Item removed from cart";
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        return null;
    }
}
