package com.example.clientConnect.product;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.portfolio.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {


    //Getting all products
    public Product[] getAllProducts(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/product/all", Product[].class);
        return responseEntity.getBody();
    }

    //add products for client with id
    public ResponseEntity<Product> getProductByClient(Long clientId) throws ProductException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product>  gottenProducts =  restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/product/id", clientId, Product.class);
        return new ResponseEntity<Product>(gottenProducts.getBody() , HttpStatus.ACCEPTED);
    }

    //add product to portfolio by portfolioId
    public ResponseEntity<Product> addProductByProductId(Long portfolioId, Product product ){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product>  sentPortfolio = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/product/proftfolioId" +portfolioId, product, Product.class);
        return new ResponseEntity<Product>(sentPortfolio.getBody(), HttpStatus.ACCEPTED);

    }

    public ResponseEntity<String> deleteProduct(Long productId) throws ProductException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginetestdb.herokuapp.com/api/v1/product/delete/"+ productId);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updateProduct(Product updatedproduct) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("https://tradeenginetestdb.herokuapp.com/api/v1/product/update", updatedproduct, Product.class);
        return new ResponseEntity<String>("Updated", HttpStatus.ACCEPTED);
    }


}
