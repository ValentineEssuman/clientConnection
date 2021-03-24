package com.example.clientConnect.product;

import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import com.example.clientConnect.portfolio.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, PortfolioService portfolioService) {
        this.productRepository = productRepository;
    }

    //Getting all products
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products;

    }

    //Getting all products with portfolio
    public List<Product> getAllProductsForPortfolio(Portfolio portfolio)  {

        List<Product> products = productRepository.findProductsByPortfolio(portfolio);

        return products;

    }

    //Getting just the Product
    public Product getProduct(Long id) throws ProductException {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ProductException("Product with id "+id+" not found")
        );

        return product;
    }


    public Product addProduct(Product product){

        product = productRepository.save(product);

        return product;
    }


    public String deleteProduct(Long id) throws ProductException {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ProductException("Product with id "+id+" not found")
        );

        productRepository.delete(product);

        return "Product successfully deleted";
    }


    @Transactional
    public void updateProduct(Long productId, Integer quantity, Double lastTradedPrice, String lastTradedSide) {
        Product product = productRepository.findProductById(productId).orElseThrow(() -> new IllegalStateException("Product with id " + productId + " does not exist."));

        if(quantity != null && !Objects.equals(product.getQuantity(),quantity)){
            product.setQuantity(quantity);
        }

        if(lastTradedPrice != null && !Objects.equals(product.getLastTradedPrice(),lastTradedPrice)){
            product.setLastTradedPrice(lastTradedPrice);
        }

        if(lastTradedSide != null && lastTradedSide.length() > 0 && !Objects.equals(product.getLastTradedSide(),lastTradedSide)){
            product.setLastTradedSide(lastTradedSide);
        }
    }
}
