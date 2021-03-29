package com.example.clientConnect.portfolio;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PortfolioService {

    //get all portfolios
    public Portfolio[] getPortfolios(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Portfolio[]> responseEntity = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/portfolio/all", Portfolio[].class);

        return responseEntity.getBody();
    }

    // adding a portfolio
    public ResponseEntity<Portfolio> addPortfolioByClientId(Long clientId,Portfolio portfolioname) throws JsonProcessingException {
        portfolioname.setClientId(clientId);
        System.out.println(portfolioname);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(portfolioname);
        System.out.println(requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<Portfolio>  sentPortfolio = restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/portfolio/new", entity, Portfolio.class);

        return new ResponseEntity<Portfolio>(sentPortfolio.getBody(), HttpStatus.ACCEPTED);

    }

    //get all portforlios for client base  by id

    public ResponseEntity<List<Portfolio>> getPortfolioById(Long clientid) throws PortfolioException, JsonProcessingException {
        Portfolio portfolio = new Portfolio();
        portfolio.setClientId(clientid);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(portfolio);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<Portfolio[]>  gottenPortfolio =  restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/portfolio/clientt/clientId", entity, Portfolio[].class);
        return new ResponseEntity<List<Portfolio>>(Arrays.asList(gottenPortfolio.getBody()) , HttpStatus.ACCEPTED);

    }


    public ResponseEntity<String> deletePortfolio(long portfolioId){
        //boolean exists = clientRepository.existsById(portfolioId);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginedb.herokuapp.com/api/v1/portfolio/delete/"+ portfolioId);

        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);

    }

}
