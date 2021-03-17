package com.example.clientConnect.product;

import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import com.example.clientConnect.portfolio.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final PortfolioService portfolioService;

    public ProductController(ProductService productService, PortfolioService portfolioService) {
        this.productService = productService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    //Getting all products with portfolio
    @GetMapping("portfolio/{portfolio_id}")
    public ResponseEntity<List<Product>> getAllProductsForPortfolio(@PathVariable Long portfolio_id) throws PortfolioException {

        List<Product> products = productService.getAllProductsForPortfolio(portfolio_id);

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    //Getting just the Product
    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ProductException {

        Product product = productService.getProduct(id);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PostMapping("/add/{portfolio_id}")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable Long portfolio_id) throws PortfolioException {

        Portfolio portfolio = portfolioService.getPortfolio(portfolio_id);

        product.setPortfolio(portfolio);

        product = productService.addProduct(product);

        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductException {

       productService.deleteProduct(id);

        return new ResponseEntity<>("Product successfully deleted",HttpStatus.ACCEPTED);
    }
}
