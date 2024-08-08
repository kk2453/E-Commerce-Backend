package com.myProject.ECommerce.service;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.ProductException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product createProduct();
    public Product deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product product) throws ProductException;
    public Product findProductById(Long Id) throws ProductException;

    public List<Product> findAllProducts();
    public List<Product> findProductsByCategory(String category);
    public List<Product> getAllProducts(String category, List<String> colours, List<String> sizes,
                                        Integer minPrice, Integer maxPrice, Integer minDiscount,
                                        String sort, String stock, Integer pageNumber, Integer pageSize);

}
