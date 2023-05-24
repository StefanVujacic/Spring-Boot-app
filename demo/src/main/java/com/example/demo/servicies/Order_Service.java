package com.example.demo.servicies;


import com.example.demo.Dao.Cart_Dao;
import com.example.demo.Dao.Order_Dao;
import com.example.demo.Dao.Product_Dao;
import com.example.demo.Dao.User_Dao;
import com.example.demo.configuration.JWT_request_filter;
import com.example.demo.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Order_Service {

    private static final String ORDER_PLACED="Placed";

    private Order_Dao order_dao;

   private Cart_Dao cart_dao;

    private Product_Dao product_dao;


    private User_Dao user_dao;

    public Order_Service() {

    }

    public Order_Service(Order_Dao order_dao, Cart_Dao cart_dao, Product_Dao product_dao, User_Dao user_dao) {
        this.order_dao = order_dao;
        this.cart_dao = cart_dao;
        this.product_dao = product_dao;
        this.user_dao = user_dao;
    }

    public Order_Dao getOrder_dao() {
        return order_dao;
    }

    public void setOrder_dao(Order_Dao order_dao) {
        this.order_dao = order_dao;
    }

    public Product_Dao getProduct_dao() {
        return product_dao;
    }

    public void setProduct_dao(Product_Dao product_dao) {
        this.product_dao = product_dao;
    }

    public User_Dao getUser_dao() {
        return user_dao;
    }

    public void setUser_dao(User_Dao user_dao) {
        this.user_dao = user_dao;
    }


    public void placeOrder(Order_Input order_input, boolean isCartCheckout) {
        List<Order_product_quantity> pro_quantity = order_input.getOrder_product_quantityList();
        for (Order_product_quantity o : pro_quantity) {
            Product product = product_dao.findById(o.getProduct_id()).get();

            String current_user = JWT_request_filter.current_user;
            User1 user = user_dao.findByUsername(current_user);
           Order_Detail order = new Order_Detail(
                    order_input.getFull_name(),
                    order_input.getFull_address(),
                    order_input.getContact_number(),
                    order_input.getAlternate_contact_number(),
                    ORDER_PLACED,
                    product,
                    user, product.getActual_price() * o.getQuantity()
            );

           if(!isCartCheckout) {
             List<Cart>cartList = cart_dao.findByUser1(user);
             cartList.forEach(x-> cart_dao.deleteById(x.getCard_id()));
           }


            order_dao.save(order);
        }
    }

    public List<Order_Detail> getOrderDetails(long orderId) {
        String currentUser= JWT_request_filter.current_user;
        User1 user = user_dao.findByUsername(currentUser);
        return order_dao.findByUser(user);
    }

    public List<Order_Detail> getAllOrderDetails (String status) {
        List<Order_Detail>order_details=new ArrayList<>();
        if (status == null) {
            order_dao.findAll().forEach(x -> order_details.add(x));
        }
        else {
            order_dao.findByOrderStatus(status).forEach(x->order_details.add(x));
        }

        return order_details;
    }

    public void markOrderAsDelivered(long id) {
           Order_Detail order_detail = order_dao.findById(id).get();

           if(order_detail != null) {
                order_detail.setOrderStatus("Delivered");
                order_dao.save(order_detail);
           }

    }



}



