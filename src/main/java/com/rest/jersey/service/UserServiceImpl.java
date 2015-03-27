package com.rest.jersey.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.jersey.dao.UserDAO;
import com.rest.jersey.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Transactional
    public List<User> getUserAll() throws ClassNotFoundException, SQLException {
        return userDAO.getUserAll();
    }

    @Transactional
    public boolean findByName(String name) throws ClassNotFoundException, SQLException {
        List<User> user = userDAO.findByName(name);
        if(!user.isEmpty()) {
            return true;
        }
        return false;
    }

    @Transactional
    public void CreateUser(User user) throws ClassNotFoundException, SQLException {
        userDAO.CreateUser(user);
    }

    @Transactional
    public boolean validateUser(String name, String password) throws ClassNotFoundException, SQLException {
        List<User> user = userDAO.findByName(name);
        if(user!=null && user.get(0).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
