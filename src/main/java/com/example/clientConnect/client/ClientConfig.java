//package com.example.clientConnect.client;
//
//import com.example.clientConnect.client.Client;
//import com.example.clientConnect.client.ClientRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.Column;
//import java.time.LocalDate;
//import java.util.List;
//
//import static java.time.Month.JANUARY;
//
//@Configuration
//public class ClientConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(ClientRepository repository){
//        return args -> {
//            Client nunana = new Client(
//                    "Nunana",
//                    "Nunana@gmail.com",
//                    "what is app",
//                    100.50,
//                    LocalDate.of(1996, JANUARY, 8)
//            );
//            Client togo = new Client(
//                    "Togo",
//                    "Togo@gmail.com",
//                    "what is app",
//                    100.50,
//                    LocalDate.of(1996, JANUARY, 8)
//            );
//            Client togoo = new Client("Togoo", "togo@gmail.com", "all is app", 2);
//
//            repository.saveAll(
//                    List.of(nunana,togo)
//            );
//
//        };
//    };
//}
