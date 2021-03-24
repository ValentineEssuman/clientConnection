package com.example.clientConnect.order;
import com.example.clientConnect.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends  JpaRepository<Order, Long>{
        List<Order> findAllByClient(Client client);
        //List<Order> findAllOrdersByStatus(String status);
        List<Order> findOrdersById(long id);

}
