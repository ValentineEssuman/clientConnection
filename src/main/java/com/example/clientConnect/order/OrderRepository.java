package com.example.clientConnect.order;
import com.example.clientConnect.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends  JpaRepository<Order, Long>{

        Optional<Order> findOrderByOrderId(Long id);
        List<Order> findOrdersByStatus(String validStatus);
        List<Order> findAllByClient(Client client);


}
