package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final ClientService clientService;

    public PortfolioController(PortfolioService portfolioService, ClientService clientService) {
        this.portfolioService = portfolioService;
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Portfolio>> getPortfolios(){
        return new ResponseEntity<>(portfolioService.getPortfolios(),HttpStatus.OK);
    }

    @GetMapping("/all/{client_id}")
    public ResponseEntity<List<Portfolio>> getPortfolios(@PathVariable("client_id") Long client_id) throws ClientException {

        List<Portfolio> portfolios= portfolioService.getPortfoliosByClient(client_id);

        return  new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolio(@RequestParam Long id) throws  PortfolioException {

        Portfolio portfolio= portfolioService.getPortfolio(id);

        return  new ResponseEntity<>(portfolio, HttpStatus.OK);
    }


    @PostMapping("/add/{client_id}")
    public ResponseEntity<Portfolio> addPortfolio(@PathVariable("client_id") Long client_id,@RequestBody Portfolio portfolio) throws ClientException {

        Client client = clientService.getClientById(client_id);

        /*portfolio.setClient(client);*/

        portfolio =  portfolioService.addPortfolio(portfolio);

        return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable("id") Long id){

        portfolioService.deletePortfolio(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
