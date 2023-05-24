package com.example.demo.entity;


import java.util.List;

public class Order_Input {

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAlternate_contact_number() {
        return alternate_contact_number;
    }

    public void setAlternate_contact_number(String alternate_contact_number) {
        this.alternate_contact_number = alternate_contact_number;
    }

    public List<Order_product_quantity> getOrder_product_quantityList() {
        return order_product_quantityList;
    }

    public void setOrder_product_quantityList(List<Order_product_quantity> order_product_quantityList) {
        this.order_product_quantityList = order_product_quantityList;
    }

    private String full_name;
    private String full_address;
    private String contact_number;
    private String alternate_contact_number;
    private List<Order_product_quantity> order_product_quantityList;



}