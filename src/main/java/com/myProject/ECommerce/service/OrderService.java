package com.myProject.ECommerce.service;

import com.myProject.ECommerce.entity.Address;
import com.myProject.ECommerce.entity.Order;
import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.OrderException;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderHistory(Long userId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder(Long orderId) throws OrderException;
    public Order cancelOrder(Long orderId) throws OrderException;
    public List<Order> getAllOrder();
    public String deleteOrder(Long orderId) throws OrderException;
}
