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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductException {

       productService.deleteProduct(id);

        return new ResponseEntity<>("Product successfully deleted",HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{productId}")
    public void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Double lastTradedPrice,
            @RequestParam(required = false) String lastTradedSide
    ) throws IllegalStateException {
        productService.updateProduct(productId,quantity,lastTradedPrice,lastTradedSide);
    }
}
