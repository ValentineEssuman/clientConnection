package com.example.clientConnect.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{


    Optional<Client> findClientByEmailAndPassword(@Param("email")String email, @Param("password")String password);

}
