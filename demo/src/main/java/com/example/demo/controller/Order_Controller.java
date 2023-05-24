package com.example.demo.controller;


import com.example.demo.entity.Order_Detail;
import com.example.demo.entity.Order_Input;
import com.example.demo.servicies.Order_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Order_Controller {
    @Autowired
    private Order_Service order_service;


    @PreAuthorize("hasRole('User')")
    @PostMapping("/place/order")
    public ResponseEntity<Void> placeOrder(@PathVariable(name = "isCartCheckout") boolean isCartCheckout,
                                           @RequestBody Order_Input order_input) {
        order_service.placeOrder(order_input, isCartCheckout);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }


    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrderDetails/{orderId}"})
    public ResponseEntity<List<Order_Detail>> getOrderDetails(@PathVariable("orderId") long orderId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-order-list", "order-list-value");
        List<Order_Detail> orderDetails = order_service.getOrderDetails(orderId);
        return new ResponseEntity<>(orderDetails, httpHeaders, HttpStatus.OK);


    }
    @PreAuthorize("hasRole('User')")
    @GetMapping("/markOrderAsDelivered/{orderId}")
    public ResponseEntity<Void> markOrderAsDelivered(@PathVariable(name = "orderId") long orderId) {
        order_service.markOrderAsDelivered(orderId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return ResponseEntity.ok().headers(httpHeaders).build();

    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrderDetails/{status}"})
    public ResponseEntity<List<Order_Detail>>getAllOrders(@PathVariable(name = "status") String status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-order-list", "order-list-value");
        List<Order_Detail> orderDetails = order_service.getAllOrderDetails(status);
        return new ResponseEntity<>(orderDetails, httpHeaders, HttpStatus.OK);

    }



}
   /* public ResponseEntity addToCart(@PathVariable (name="productId")long productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product-header", "product-response-value");
        return new ResponseEntity (cartService.addToCart(productId),httpHeaders,200);
    } */
