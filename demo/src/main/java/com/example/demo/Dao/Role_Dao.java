package com.example.demo.Dao;


import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface Role_Dao extends JpaRepository<Role, Long> {

    Optional<Role>findById(Long id);


}
