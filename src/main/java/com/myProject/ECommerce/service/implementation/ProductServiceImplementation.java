package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.service.ProductService;

import java.util.List;

public class ProductServiceImplementation implements ProductService {

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductException {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        return null;
    }

    @Override
    public Product findProductById(Long Id) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getAllProducts(String category, List<String> colours, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        return List.of();
    }
}
