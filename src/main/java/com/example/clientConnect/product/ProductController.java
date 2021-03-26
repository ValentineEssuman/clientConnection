package com.example.clientConnect.product;


import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.portfolio.PortfolioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/portfolio/{id}")
    public Product[] getAllProducts(@PathVariable("id") long id){
        return productService.getProductsPortfolio(id);

    }

    @PostMapping("/add/{portfolio_Id}")
    public void addProduct(@PathVariable("portfolio_Id") Long portfolio_Id,@RequestBody Product product) throws ProductException, JsonProcessingException {
        productService.addProductByProductId(portfolio_Id, product);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductException {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateClient(
            @PathVariable("productId") Long productId,
            @RequestParam Integer quantity,
            @RequestParam Double lastTradedPrice,
            @RequestParam String lastTradedSide
    ) throws ClientException {
        Product updatedProduct = new Product(quantity, lastTradedPrice, lastTradedSide);
        System.out.println(updatedProduct);
        return productService.updateProduct(productId,updatedProduct);
    }
}
