package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> color,
                                                                                             @RequestParam List<String> size, @RequestParam Integer minPrice,
                                                                                             @RequestParam Integer maxPrice, @RequestParam Integer mixDiscount,
                                                                                             @RequestParam String sort, @RequestParam String stock,
                                                                                             @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        Page<Product> res = productService.getAllProducts(category,color,size,minPrice,maxPrice,mixDiscount,sort,stock,pageNumber,pageSize);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
