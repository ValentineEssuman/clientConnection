package com.example.clientConnect.admin;

import com.example.clientConnect.client.AdminException;
import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import com.example.clientConnect.portfolio.PortfolioService;
import com.example.clientConnect.product.Product;
import com.example.clientConnect.product.ProductException;
import com.example.clientConnect.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="api/admin")
public class AdminController {

    private final AdminService adminService;
    private final ClientService clientService;
    private final PortfolioService portfolioService;
    private final ProductService productService;

    public AdminController(AdminService adminService,
                           ClientService clientService,
                           PortfolioService portfolioService,
                           ProductService productService) {
        this.adminService = adminService;
        this.clientService = clientService;
        this.portfolioService = portfolioService;
        this.productService = productService;
    }


    @PostMapping("/login")
    public ResponseEntity<Admin> loginClient(@RequestBody Admin admin) throws AdminException {

        admin = adminService.loginAdmin(admin);
        return  new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/clientOrder")
    public ResponseEntity<String> getClientsOrders(){
        String orders = adminService.getClientOrder();
        return new ResponseEntity<String>(orders, HttpStatus.OK);
    }

    //Which trades are open/cancelled/failed/ successful and filled?

    @GetMapping("/clientOrder/success")
    public ResponseEntity<String> getSuccessfulOrders(){
        String successOrders = adminService.getSuccessOrders();
        return new ResponseEntity<String>(successOrders, HttpStatus.OK);
    }

    @GetMapping("/clientOrder/failed")
    public ResponseEntity<String> getFailedOrders(){
        String failedOrders = adminService.getFailedOrders();
        return new ResponseEntity<String>(failedOrders, HttpStatus.OK);
    }

    @GetMapping("/trades/open")
    public ResponseEntity<String> getOpenTrades(){
        String openTrades = adminService.getOpenTrades();
        return new ResponseEntity<String>(openTrades, HttpStatus.OK);
    }

    //checking filled/pending client trades
    @GetMapping("/trades/pending")
    public ResponseEntity<String> getPendingTrades(){
        String pendingTrades = adminService.getPendingTrades();
        return new ResponseEntity<String>(pendingTrades, HttpStatus.OK);
    }

    /**
     * Admin client routes
     */
    @GetMapping("/client/all")
    public ResponseEntity<List<Client>> getAllClients(){

        List<Client> clients =  clientService.getClients();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/client/{client_id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long client_id) throws ClientException {

        Client client =  clientService.getClientById(client_id);

        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    /**
     * Admin portfolio routes
     */

    //Getting all portfolios
    @GetMapping("/portfolio/all")
    public ResponseEntity<List<Portfolio>> getAllPortfolios(){

        List<Portfolio> portfolios =  portfolioService.getPortfolios();

        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    //Getting list of portfolios by client
    @GetMapping("/portfolio/client/{client_id}/all")
    public ResponseEntity<List<Portfolio>> getAllPortfoliosByClient(@PathVariable Long client_id) throws ClientException {

        List<Portfolio> portfolios =  portfolioService.getPortfoliosByClient(client_id);

        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    //Getting portfolio by id
    @GetMapping("/portfolio/{id}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable Long id) throws PortfolioException {
        Portfolio portfolio = portfolioService.getPortfolio(id);

        return new ResponseEntity<>(portfolio,HttpStatus.OK);
    }

    /**
     * Admin products routes
     */
    //Getting all portfolios
    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products =  productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Getting all products by portfolio
    @GetMapping("/product/portfolio/{portfolio_id}")
    public ResponseEntity<List<Product>> getAllProductsByPortfolio(@PathVariable Long portfolio_id) throws PortfolioException {

        List<Product> products =  productService.getAllProductsForPortfolio(portfolio_id);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//    //Getting all products by clients
    @GetMapping("/product/client/{client_id}")
    public ResponseEntity<List<Product>> getAllProductsByClient(@PathVariable Long client_id) throws  ClientException {

        List<Product> products =  productService.getAllProductsForClient(client_id);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ProductException {

        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);

    }



}
