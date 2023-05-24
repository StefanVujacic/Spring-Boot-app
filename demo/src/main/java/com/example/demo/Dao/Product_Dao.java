package com.example.demo.Dao;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Product_Dao extends JpaRepository<Product,Long> {


 Page<Product> findAll(Pageable pageable);
 Page<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(String key1, String key2, Pageable pageable);

}
