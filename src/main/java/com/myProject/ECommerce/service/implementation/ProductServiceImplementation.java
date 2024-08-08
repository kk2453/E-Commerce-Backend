package com.myProject.ECommerce.service.implementation;

import com.myProject.ECommerce.entity.Category;
import com.myProject.ECommerce.entity.Product;
import com.myProject.ECommerce.exception.ProductException;
import com.myProject.ECommerce.repository.CategoryRepository;
import com.myProject.ECommerce.repository.ProductRepository;
import com.myProject.ECommerce.request.CreateProductRequest;
import com.myProject.ECommerce.service.ProductService;
import com.myProject.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest request) {

        Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());
        if(topLevel==null){
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel =categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(), topLevel.getName());
        if(secondLevel==null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel=categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdLevelCategory(), secondLevel.getName());
        if(thirdLevel==null){
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel=categoryRepository.save(thirdLevelCategory);
        }

        Product product = Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(String.valueOf(request.getPrice()))
                .discountedPrice(request.getDiscountedPrice())
                .discountPercent(request.getDiscountPercent())
                .quantity(request.getQuantity())
                .brand(request.getBrand())
                .color(request.getColour())
                .sizes(request.getSizes())
                .imageUrl(request.getImageUrl())
                .category(thirdLevel)
                .createdAt(LocalDateTime.now())
                .build();

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product request) throws ProductException {
        Product productById = findProductById(productId);
        if(request.getQuantity()!=0){
            productById.setQuantity(request.getQuantity());
        }
        return productRepository.save(productById);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent())return opt.get();
        else throw new ProductException("Product not found with id: "+id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colours, List<String> sizes,
                                        Integer minPrice, Integer maxPrice, Integer minDiscount,
                                        String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Product> products =productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);

        if(!colours.isEmpty()){
            products = products.stream().filter(p->colours.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }

        if(stock!=null){
            if(stock.equalsIgnoreCase("in_stock")){
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if(stock.equalsIgnoreCase("out_of_stock")){
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex =(int)pageable.getOffset();
        int endIndex =Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product> pageContent = products.subList(startIndex,endIndex);
        Page<Product>filteredProducts = new PageImpl<>(pageContent,pageable, products.size());

        return filteredProducts;
    }
}
