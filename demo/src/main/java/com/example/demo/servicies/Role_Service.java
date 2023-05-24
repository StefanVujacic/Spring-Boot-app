package com.example.demo.servicies;




import com.example.demo.Dao.Role_Dao;
import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Role_Service {


    private Role_Dao roleDao;

    public Role_Service() {

    }
    public Role_Service(Role_Dao roleDao) {
        this.roleDao = roleDao;
    }

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}