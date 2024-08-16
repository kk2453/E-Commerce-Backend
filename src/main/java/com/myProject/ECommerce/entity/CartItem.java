package com.myProject.ECommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    private String size;
    private int quantity;
    private Integer price;
    private Integer discountedPrice;
    private Long userId;
}
