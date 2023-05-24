package com.example.demo.Dao;


import com.example.demo.entity.Order_Detail;

import com.example.demo.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Order_Dao extends JpaRepository<Order_Detail,Long> {

   List<Order_Detail>findByUser(User1 user);
   List<Order_Detail>findByOrderStatus(String status);
}
