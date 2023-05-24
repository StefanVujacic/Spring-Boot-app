package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Cart  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private long card_id;
    @OneToOne
    private Product product;
    @OneToOne
    private User1 user1;

    public Cart() {

    }

    public Cart(Product product, User1 user1) {
        this.product = product;
        this.user1 = user1;
    }

    public long getCard_id() {
        return card_id;
    }

    public void setCard_id(long card_id) {
        this.card_id = card_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User1 getUser1() {
        return user1;
    }

    public void setUser1(User1 user1) {
        this.user1 = user1;
    }



}
