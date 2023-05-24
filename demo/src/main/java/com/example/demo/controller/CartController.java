package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.servicies.CartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    private CartService cartService;

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{productId}"})
    public ResponseEntity addToCart(@PathVariable (name="productId")long productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product-header", "product-response-value");
        return new ResponseEntity (cartService.addToCart(productId),httpHeaders,200);
    }


    @PreAuthorize("hasRole('User')")
    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public ResponseEntity deleteCartItem(@PathVariable (name="cartId") long cartId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("my-response", "my-response-value");
        return new ResponseEntity<>(cartService.deleteCartItem(cartId), httpHeaders,200);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getCardDetails"})
    public ResponseEntity<List<Cart>> getCardDetails () {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return new ResponseEntity<>(cartService.getCartDetails(), httpHeaders, 200);
    }



}
