package com.myProject.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private String orderId;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime orderDate;
    private String orderStatus;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address shippingAddress;

    private double totalPrice;
    private Integer totalDiscountedPrice;
    private Integer totalDiscount;
    private int totalItem;
    private LocalDateTime createdAt;

}
