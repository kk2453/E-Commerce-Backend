package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.OrderItem;
import com.myProject.ECommerce.repository.OrderItemRepository;
import com.myProject.ECommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
