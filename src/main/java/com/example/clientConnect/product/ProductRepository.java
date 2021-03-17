package com.example.clientConnect.product;

import com.example.clientConnect.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductsByPortfolio(Portfolio portfolio);
}
