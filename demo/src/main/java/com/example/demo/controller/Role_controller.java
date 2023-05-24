package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.servicies.Role_Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Role_controller {

    private Role_Service roleService;

    public Role_controller(Role_Service roleService) {
        this.roleService = roleService;
    }
    public Role_controller() {

    }

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}




