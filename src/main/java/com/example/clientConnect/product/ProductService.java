package com.example.clientConnect.product;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import com.example.clientConnect.portfolio.PortfolioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PortfolioService portfolioService;
    private final ClientService clientService;

    public ProductService(ProductRepository productRepository,
                          PortfolioService portfolioService,
                          ClientService clientService) {
        this.productRepository = productRepository;
        this.portfolioService = portfolioService;
        this.clientService = clientService;
    }

    //Getting all products
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products;

    }

    //Getting all products with portfolio
    public List<Product> getAllProductsForPortfolio(Long portfolio_id) throws PortfolioException {

        Portfolio portfolio = portfolioService.getPortfolio(portfolio_id);

        List<Product> products = productRepository.findProductsByPortfolio(portfolio);

        return products;

    }

    //Getting all products with portfolio
    public List<Product> getAllProductsForClient(Long client_id) throws  ClientException {

        List<Long> portfolioIds = portfolioService.getPortfoliosByClient(client_id)
                                                  .stream()
                                                   .map(Portfolio::getId)
                                                    .collect(Collectors.toList());

        List<Product> products = productRepository.findAllById(portfolioIds);

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


}
