package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.*;
import com.myProject.ECommerce.exception.OrderException;
import com.myProject.ECommerce.repository.*;
import com.myProject.ECommerce.service.CartService;
import com.myProject.ECommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddresses().add(address);
        userRepository.save(user);
        Cart cart = cartService.findUserCart(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem  item : cart.getCartItems()){
            OrderItem orderItem = OrderItem.builder()
                    .product(item.getProduct())
                    .size(item.getSize())
                    .quantity(item.getQuantity())
                    .price(item.getPrice())
                    .discountedPrice(item.getDiscountedPrice())
                    .userId(item.getUserId())
                    .build();
            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }

        Order createdOrder = Order.builder()
                .user(user)
                .orderItems(orderItems)
                .orderDate(LocalDateTime.now())
                .orderStatus("PENDING")
                .shippingAddress(address)
                .totalPrice(cart.getTotalPrice())
                .totalDiscountedPrice(cart.getTotalDiscountedPrice())
                .totalDiscount(cart.getDiscount())
                .totalItem(cart.getTotalItem())
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(createdOrder);
        for(OrderItem item : orderItems){
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> optional = orderRepository.findById(orderId);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new OrderException("Order does not exist with the id: "+orderId);
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        List<Order> orders = orderRepository.getUsersOrders(userId);
        return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        return orderRepository.save(order);
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public String deleteOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
        return "Order deleted successfully";
    }

}
