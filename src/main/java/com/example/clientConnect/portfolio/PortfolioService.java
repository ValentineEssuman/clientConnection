package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final ClientService clientService;

    public PortfolioService(PortfolioRepository portfolioRepository, ClientService clientService) {
        this.portfolioRepository = portfolioRepository;
        this.clientService = clientService;
    }

    public List<Portfolio> getPortfolios(){
        return portfolioRepository.findAll();
    }

    public List<Portfolio> getPortfoliosByClient(Long client_id) throws ClientException {
        Client client = clientService.getClientById(client_id);
        return portfolioRepository.findAllByClient(client);
    }

    public Portfolio addPortfolio(Portfolio portfolio){

        return portfolioRepository.save(portfolio);
    }

    public Portfolio getPortfolio(Long id) throws PortfolioException {

        return portfolioRepository.findById(id).orElseThrow(
                ()-> new PortfolioException("Portfolio with id "+id+" does not exist")
        );
    }

    public void deletePortfolio(Long id){
        portfolioRepository.deleteById(id);
    }

}
