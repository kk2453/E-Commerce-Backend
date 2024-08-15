package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Address;
import com.myProject.ECommerce.entity.Order;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.OrderException;
import com.myProject.ECommerce.repository.CartRepository;
import com.myProject.ECommerce.repository.OrderRepository;
import com.myProject.ECommerce.service.CartItemService;
import com.myProject.ECommerce.service.OrderService;
import com.myProject.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return List.of();
    }

    @Override
    public String deleteOrder(Long orderId) throws OrderException {
        return "Order deleted successfully";
    }

}
