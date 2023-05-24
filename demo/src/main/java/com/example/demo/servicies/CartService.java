package com.example.demo.servicies;

import com.example.demo.Dao.Cart_Dao;
import com.example.demo.Dao.Product_Dao;
import com.example.demo.Dao.User_Dao;
import com.example.demo.configuration.JWT_request_filter;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User1;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    public CartService(Cart_Dao cart_dao, Product_Dao product_dao, User_Dao user_dao) {
        this.cart_dao = cart_dao;
        this.product_dao = product_dao;
        this.user_dao = user_dao;
    }

    public CartService() {

    }

    private Cart_Dao cart_dao;


    private Product_Dao product_dao;


    private User_Dao user_dao;

    public String deleteCartItem(long cartId){
        cart_dao.deleteById(cartId);
        return "deleted";
    }

    public Cart addToCart(Long product_id) {
        Product product = product_dao.findById(product_id).get();
        String username = JWT_request_filter.current_user;
        Optional<User1>user1= Optional.empty();
        if (username != null) {
            user1 = Optional.ofNullable(user_dao.findByUsername(username));
        }

        List<Cart>cartList=cart_dao.findByUser1(user1.get());
        List<Cart> filteredList = cartList.stream().filter(x-> x.getProduct().getId() ==product_id).collect(Collectors.toList());

        if(filteredList.size()>0) {
            return null;
        }

        if (product != null && user1 != null) {
            Cart cart = new Cart(product, user1.get());
            return cart_dao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails() {
        String username = JWT_request_filter.current_user;
        if (username != null) {
            User1 user1 = user_dao.findByUsername(username);
           return cart_dao.findByUser1(user1);
        } else {
            return null;
        }

    }
}
