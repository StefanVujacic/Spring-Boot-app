package com.example.demo.Dao;

import com.example.demo.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface User_Dao extends JpaRepository<User1, Long> {





    User1 findById(long id);


    User1 findByUsername(String currentUser);
}
