package com.example.demo.servicies;

import com.example.demo.Dao.Role_Dao;
import com.example.demo.Dao.User_Dao;
import com.example.demo.configuration.CustomUserDetailsService;
import com.example.demo.entity.Role;
import com.example.demo.entity.User1;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;



@Service
public class User_Service {

    private User_Dao user_dao;
    private Role_Dao roleDao;

    public User_Service() {
    }

    public User_Service(User_Dao user_dao, Role_Dao roleDao, CustomUserDetailsService customUserDetailsService) {
        this.user_dao = user_dao;
        this.roleDao = roleDao;
        this.customUserDetailsService = customUserDetailsService;
    }


    CustomUserDetailsService customUserDetailsService;


    public User1 register_user(User1 user) {
        if (customUserDetailsService != null) {
            customUserDetailsService.loadUserByUsername(user.getUsername());
        }
        return user_dao.save(user);
    }


    public User1 registerNewUser(User1 user) {
        try {
            Role role = roleDao.findById(1L).get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setPassword(user.getPassword());
            return user_dao.save(user);
        } catch (Exception ex) {
            System.out.println("No such element");
        }

        return null;
    }

}




