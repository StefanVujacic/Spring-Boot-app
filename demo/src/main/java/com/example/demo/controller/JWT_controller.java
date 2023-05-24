package com.example.demo.controller;


import com.example.demo.configuration.CustomUserDetailsService;
import com.example.demo.entity.JWT_Request;
import com.example.demo.entity.JWT_response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JWT_controller {

    @Autowired
    private CustomUserDetailsService service;

        @PostMapping({"/authenticate"})
        public JWT_response CreateJwT_Token (@RequestBody JWT_Request jwt_request) {
            try {
                return service.jwt_response(jwt_request);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }




}
