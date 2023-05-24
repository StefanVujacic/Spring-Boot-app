package com.example.demo.configuration;

import com.example.demo.Dao.User_Dao;
import com.example.demo.entity.JWT_Request;
import com.example.demo.entity.JWT_response;
import com.example.demo.entity.User1;
import com.example.demo.util.JWT_util;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private User_Dao user_dao;


    private JWT_util jwt_util;

    public User_Dao getUser_dao() {
        return user_dao;
    }

    public void setUser_dao(User_Dao user_dao) {
        this.user_dao = user_dao;
    }

    public JWT_response create_Jwt_token;


    public AuthenticationManager authenticationManager;

    public CustomUserDetailsService() {

    }

    public CustomUserDetailsService(User_Dao user_dao, JWT_util jwt_util, JWT_response create_Jwt_token) {
        this.user_dao = user_dao;
        this.jwt_util = jwt_util;
        this.create_Jwt_token = create_Jwt_token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User1 user = user_dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }


    private JWT_response authenticate(String username, String password) throws Exception {
        try {
            return (JWT_response) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new Exception("User is disabled");
        } catch (BadCredentialsException bad) {
            throw new Exception("Bad credentials from user");
        }

    }

    public JWT_response jwt_response(JWT_Request jwt_request) throws Exception {
        String username = jwt_request.getUserName();
        String password = jwt_request.getPassword();
        authenticate(username, password);
        final UserDetails userDetails = loadUserByUsername(username);
        String new_generated_token = jwt_util.generate_token(userDetails);
        User1 user = user_dao.findByUsername(username);
        return new JWT_response(new_generated_token, user);
    }

}
