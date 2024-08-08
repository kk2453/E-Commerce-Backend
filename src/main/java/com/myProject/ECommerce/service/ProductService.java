package com.myProject.ECommerce.service;

import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.request.CreateProductRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product createProduct(CreateProductRequest request);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product request) throws ProductException;
    public Product findProductById(Long id) throws ProductException;

    public List<Product> findAllProducts();
    public List<Product> findProductsByCategory(String category);
    public Page<Product> getAllProducts(String category, List<String> colours, List<String> sizes,
                                        Integer minPrice, Integer maxPrice, Integer minDiscount,
                                        String sort, String stock, Integer pageNumber, Integer pageSize);

}
