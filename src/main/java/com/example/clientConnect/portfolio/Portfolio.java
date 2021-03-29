package com.example.clientConnect.portfolio;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Portfolio {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long portfolioId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long clientId;


    public Portfolio() {
    }

    public Portfolio(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Portfolio(String name) {
        this.name = name;
    }

    public Portfolio(String name, long clientId) {
        this.name = name;
        this.clientId = clientId;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }


    @Override
    public String toString() {
        return "Portfolio{" +
                "portfolioId=" + portfolioId +
                ", name='" + name + '\'' +
                ", clientId=" + clientId +
                '}';
    }

}
