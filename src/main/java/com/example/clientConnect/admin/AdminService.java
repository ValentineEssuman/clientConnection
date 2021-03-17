package com.example.clientConnect.admin;

import com.example.clientConnect.client.AdminException;
import com.example.clientConnect.client.Client;
import com.sun.istack.NotNull;
import org.hibernate.hql.internal.ast.tree.IndexNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminService {

    private final AdminRepository adminRepository;


    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;

    }

    public List<Admin> findAdmins(){
        return adminRepository.findAll();
    }

    //add a new admin
    public Admin addAdmin(@NotNull Admin admin){
        Admin newadmin = adminRepository.save(admin);
        return newadmin;
    }

    //login
    public Admin loginAdmin(@NotNull Admin admin) throws AdminException {

        return adminRepository.findAdminByEmailAndPassword(admin.getEmail(), admin.getPassword()).orElseThrow(
                ()-> new AdminException("Admin does not exist or wrong input of admin")
        );

    }

    // not sure of the use of these but these since the query will not interact with JPA
    public String getClientOrder() {
        return "all orders";
    }

    public String getSuccessOrders() {
        return "all succesfull orders";
    }

    public String getFailedOrders() {
        return "failed orders";
    }


    // get from JPA or from
    public String getOpenTrades() {
        return "Open Orders";
    }

    public String getPendingTrades() {
        return "Pending Orders ";
    }
}

