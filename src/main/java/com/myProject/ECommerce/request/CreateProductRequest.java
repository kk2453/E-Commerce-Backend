package com.myProject.ECommerce.request;


import com.myProject.ECommerce.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequest {

    private String title;
    private String description;
    private String brand;
    private String colour;


    private int price;
    private int discountedPrice;
    private int discountPercent;

    private int quantity;
    private Set<Size> sizes = new HashSet<>();
    private String imageUrl;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;


}
