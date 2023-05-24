package com.example.demo.controller;

import com.example.demo.entity.User1;
import com.example.demo.servicies.User_Service;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class User_Controller {
       User_Service user_service;

    public User_Controller(User_Service user_service) {
        this.user_service = user_service;
    }

    @PreAuthorize("hasRole('User')")
    @PostMapping("/register_User")
    public User1 register_User(@RequestBody User1 user) {
        return user_service.register_user(user);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/for admin")
    public String url_admin () {
        return "this URL is accessible only to admin";
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/for user")
    public String url_user () {
        return "this URL is accessible only to user";
    }

    @PreAuthorize("hasRole('User')")
    @PostMapping ({"/register_new_user"})
    public User1 register_new_user(@RequestBody User1 user1) {
       return user_service.registerNewUser(user1);
    }




}
