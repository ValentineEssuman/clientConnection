package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.order.Order;
import com.example.clientConnect.product.Product;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
//@Transactional
public class Portfolio {

    @Id
/*    @SequenceGenerator(
            name="portfolio_sequence", sequenceName = "portfolio_sequence",
            allocationSize = 1
    )*/
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientID")
    private Client client;

    // client can place orders to make portforlio
    @OneToMany(mappedBy="portfolio",cascade = CascadeType.ALL)
    private List<Product> products;

    private LocalDate created_at = LocalDate.now();

    public Portfolio(String name,
                     Client client) {
        this.name = name;
        this.client = client;

    }

    public Portfolio(String name) {
        this.name = name;
    }

    public Portfolio() {

    }

    public Long getId() {
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
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client=" + client +
                ", products=" + products +
                ", created_at=" + created_at +
                '}';
    }
}
