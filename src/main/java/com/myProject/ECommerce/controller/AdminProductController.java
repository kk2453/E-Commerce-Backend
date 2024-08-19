package com.myProject.ECommerce.controller;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.request.CreateProductRequest;
import com.myProject.ECommerce.response.ApiResponse;
import com.myProject.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request){
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
        productService.deleteProduct(productId);
        ApiResponse response = ApiResponse.builder()
                .message("Product deleted successfully")
                .status(true)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product request, @PathVariable Long productId) throws ProductException{
        Product product = productService.updateProduct(productId, request);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProducts(@RequestBody CreateProductRequest[] requests){
        for(CreateProductRequest productRequest : requests){
            productService.createProduct(productRequest);
        }
        ApiResponse response = ApiResponse.builder()
                .message("Products created successfully")
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
