package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.order.Order;
import com.example.clientConnect.product.Product;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "portfolioId",
        scope     = Long.class)
public class Portfolio {


    private long portfolioId;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long clientId;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    @JsonIdentityReference(alwaysAsId = true)
    private Client client;

    // client can place orders to make portforlio
    @JsonIdentityReference(alwaysAsId = true)
    private List<Product> products = new ArrayList<>();

    @JsonIdentityReference(alwaysAsId = true)
    private List<Order> clientOrders = new ArrayList<>();

    //private LocalDateTime createdAt = LocalDateTime.now();

/*    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientID")
    private Client client;

    // client can place orders to make portforlio
    @OneToMany(mappedBy="portfolio",cascade = CascadeType.ALL)
    private List<Product> products;

    private LocalDate created_at = LocalDate.now();*/

    public Portfolio() {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }

    public List<Order> getOrders() {
        return clientOrders;
    }

    public void setOrders(Order clientOrder) {
        clientOrders.add(clientOrder);
    }




/*    public Portfolio(String name,
                     Client client) {
        this.name = name;
        this.client = client;

    }

    public Portfolio(String name) {
        this.name = name;
    }

    public Portfolio() {

    }*/

/*    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }*/

    @Override
    public String toString() {
        return "Portfolio{" +
                "portfolioId=" + portfolioId +
                ", name='" + name + '\'' +
                ", clientId=" + clientId +
                ", client=" + client +
                ", products=" + products +
                ", clientOrders=" + clientOrders +
                '}';
    }

/*  @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client=" + client +
                ", products=" + products +
                ", created_at=" + created_at +
                '}';
    }*/
}
