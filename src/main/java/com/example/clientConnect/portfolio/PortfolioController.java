package com.example.clientConnect.portfolio;


import com.example.clientConnect.client.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService, ClientService clientService) {
        this.portfolioService = portfolioService;
    }

    //get all portfolios
    @GetMapping("/all")
    public  Portfolio[] getPortfolios(){
        return portfolioService.getPortfolios();
    }

    //add portfolio based on client id
    public ResponseEntity<Portfolio> addPortfolio(@PathVariable("client_id") Long client_id,@RequestBody Portfolio portfolio) throws PortfolioException, JsonProcessingException {
        portfolioService.addPortfolioByClientId(client_id, portfolio);
        return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }

    // get porfolio based on client id
    @GetMapping("/{client_id}")
    public ResponseEntity<List<Portfolio>> getPortfolios(@PathVariable("client_id") Long client_id) throws PortfolioException, JsonProcessingException {
        return portfolioService.getPortfolioById(client_id);
    }

    // deleting client porfolio based on cliend id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable("id") Long id){
        portfolioService.deletePortfolio(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
