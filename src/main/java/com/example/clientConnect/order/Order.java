package com.example.clientConnect.order;

import com.example.clientConnect.client.Client;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

//Trading order from client
@Entity
@Table
@Transactional
public class Order {
    @Id
    @SequenceGenerator(
            name="order_sequence", sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )

    @Column(nullable = false,updatable = false)
   private Long id;
    @Column(nullable = false)
   private String product;
    @Column(nullable = false)
   private String side;
    @Column(nullable = false, updatable = false)
   private Double price;
    @Column(nullable = false, updatable = false)
   private Integer quantity;
   private Integer validStatus;
    @Column(nullable = false, updatable = false)
   private Integer portfolioid;
   public  LocalDate created = LocalDate.now();
    //@ManyToOne
    private Client client;


    public Order(Long id, String product, String side, Double price, Integer quantity, Integer portfolioid) {
        this.id = id;
        this.product = product;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.portfolioid = portfolioid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
    }

    public Integer getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(Integer portfolioid) {
        this.portfolioid = portfolioid;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
