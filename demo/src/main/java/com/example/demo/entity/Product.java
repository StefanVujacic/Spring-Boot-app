package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {
    public boolean getProductId;

    public Set<Image_model> getProduct_images() {
        return product_images;
    }

    public Product() {

    }

    public Product(String productName, String productDescription, double discount_price, double actual_price, Set<Image_model> product_images) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.discount_price = discount_price;
        this.actual_price = actual_price;
        this.product_images = product_images;
    }

    public Product(long id, String productName, String productDescription, double discount_price, double actual_price, Set<Image_model> product_images) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.discount_price = discount_price;
        this.actual_price = actual_price;
        this.product_images = product_images;
    }

    public void setProduct_images(Set<Image_model> product_images) {
        this.product_images = product_images;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String productName;
    @Column(length = 20000)
    private String productDescription;
    private double discount_price;
    @Column(nullable = false)
    private double actual_price;

    @ManyToMany(fetch= FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name ="Product_Images", joinColumns = {
            @JoinColumn(name="product_id")
    },
            inverseJoinColumns={
          @JoinColumn(name="image_id")
    }
    )
    private Set<Image_model>product_images;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public double getActual_price() {
        return actual_price;
    }

    public void setActual_price(double actual_price) {
        this.actual_price = actual_price;
    }


}
