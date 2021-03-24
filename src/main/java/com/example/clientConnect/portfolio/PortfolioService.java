package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PortfolioService {

    //get all portfolios
    public Portfolio[] getPortfolios(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Portfolio[]> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/all", Portfolio[].class);

        return responseEntity.getBody();
    }

    // adding a portfolio
    public ResponseEntity<Portfolio> addPortfolio(Portfolio portfolioname){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Portfolio>  sentPortfolio = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/id", portfolioname, Portfolio.class);
        return new ResponseEntity<Portfolio>(sentPortfolio.getBody(), HttpStatus.ACCEPTED);

    }

    //get all portforlios for client base  by id
    public ResponseEntity<Portfolio> getPortfolioById(Long clientid) throws PortfolioException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Portfolio>  gottenPortfolio =  restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/portfolio/id", clientid, Portfolio.class);
        return new ResponseEntity<Portfolio>(gottenPortfolio.getBody() , HttpStatus.ACCEPTED);
    }

    //deleting a client
    public ResponseEntity<String> deleteClient(Long clientid) {
        //boolean exists = clientRepository.existsById(clientid);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginetestdb.herokuapp.com/api/v1/portfolio/uregister/"+ clientid);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }


    public ResponseEntity<String> deletePortfolio(Long portfolioId){
        //boolean exists = clientRepository.existsById(portfolioId);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginetestdb.herokuapp.com/api/v1/portfolio/delete/"+ portfolioId);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);

    }

}
