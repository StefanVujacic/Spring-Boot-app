package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
public class Order_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private String order_name;
    private String address;
    private String order_contact_number;
    private String order_alternate_contact_number;
    private String orderStatus;
    @OneToOne
    private Product product;
    @OneToOne
    private User1 user;

    public Order_Detail() {

    }




    public Order_Detail(String order_name, String address, String order_contact_number, String order_alternate_contact_number, String orderStatus, Product product, User1 user, double order_amount) {
        this.order_name = order_name;
        this.address = address;
        this.order_contact_number = order_contact_number;
        this.order_alternate_contact_number = order_alternate_contact_number;
        this.orderStatus = orderStatus;
        this.product = product;
        this.user = user;
        this.order_amount = order_amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long order_id) {
        this.orderId = orderId;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrder_contact_number() {
        return order_contact_number;
    }

    public void setOrder_contact_number(String order_contact_number) {
        this.order_contact_number = order_contact_number;
    }

    public String getOrder_alternate_contact_number() {
        return order_alternate_contact_number;
    }

    public void setOrder_alternate_contact_number(String order_alternate_contact_number) {
        this.order_alternate_contact_number = order_alternate_contact_number;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(double order_amount) {
        this.order_amount = order_amount;
    }

    private double order_amount;

}
