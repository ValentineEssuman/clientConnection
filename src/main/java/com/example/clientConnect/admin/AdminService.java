package com.example.admin.admin;

import com.sun.istack.NotNull;
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
    public Admin loginAdmin(@NotNull Admin admin){

        return adminRepository.findAdminByEmailAndPassword(admin.getEmail(), admin.getPassword()).orElseThrow(
                ()-> new IllegalArgumentException("Admin does not exist or wrong input of admin")
        );

    }

}
