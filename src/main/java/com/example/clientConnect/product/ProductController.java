package com.example.clientConnect.product;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
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


    //get all products
    @GetMapping("/all")
    public Product[] getAllProducts(){
        return productService.getAllProducts();

    }

    // get porfolio based on client id
    @GetMapping("/{client_id}")
    public ResponseEntity<Product> getClientProducts(@PathVariable("client_id") Long client_id) throws ProductException {
        return productService.getProductByClient(client_id);
    }

    @PostMapping("/add/{portfolio_Id}")
    public ResponseEntity<Product> addProduct(@PathVariable("portfolio_Id") Long portfolio_Id,@RequestBody Product product) throws ProductException {
        productService.addProductByProductId(portfolio_Id, product);
        return new ResponseEntity<>(product,HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product successfully deleted",HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateClient(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Double lastTradedPrice,
            @RequestParam(required = false) String lastTradedSide
    ) throws ClientException {
        Product updatedProduct = new Product(productId, quantity, lastTradedPrice, lastTradedSide);
        productService.updateProduct(updatedProduct);
        return new ResponseEntity<> (updatedProduct, HttpStatus.ACCEPTED);
    }
}
