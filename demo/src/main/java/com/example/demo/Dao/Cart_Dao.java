package com.example.demo.Dao;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface Cart_Dao extends JpaRepository<Cart, Long> {
     List<Cart> findByUser1(User1 user1);
}
