package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> getPortfolios(Client client) {
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
