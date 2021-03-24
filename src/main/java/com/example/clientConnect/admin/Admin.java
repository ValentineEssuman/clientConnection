package com.example.clientConnect.admin;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
//main idea Abstract class appuser with subclasses client and Admin

//however in following your suggestion we get the code below


@Entity
@Table
@Transactional
public class Admin {
    @Id
/*    @SequenceGenerator(
            name="admin_sequence", sequenceName = "admin_sequence",
            allocationSize = 1
    )*/
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            //generator = "admin_sequence"
    )

    @Column(nullable = false,updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate created_at = LocalDate.now();

    public Admin(String name, String email, String password, LocalDate created_at) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Admin() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
