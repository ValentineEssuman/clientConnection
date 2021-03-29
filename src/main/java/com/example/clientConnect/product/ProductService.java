package com.example.clientConnect.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {


    //Getting all products
    public Product[] getAllProducts(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/product/all", Product[].class);
        return responseEntity.getBody();
    }

    public Product[] getProductsPortfolio(long portfolioId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/product/portfolioId/"+portfolioId, Product[].class);
        return responseEntity.getBody();
    }

    //add product to portfolio by portfolioId
    public void addProductByProductId(Long portfolioId, Product product ) throws JsonProcessingException {
        product.setPortfolioId(portfolioId);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(product);
        System.out.println(requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/product/new", entity, Product.class);
    }

    public ResponseEntity<String> deleteProduct(Long productId) throws ProductException {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("https://tradeenginedb.herokuapp.com/api/v1/product/delete/"+ productId);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updateProduct(long productId, Product updatedproduct) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> entity = new HttpEntity<>(updatedproduct,headers);
        restTemplate.exchange("https://tradeenginedb.herokuapp.com/api/v1/product/update/"+productId,HttpMethod.PUT,entity,void.class);
        return new ResponseEntity<String>("Updated", HttpStatus.ACCEPTED);
    }
}
