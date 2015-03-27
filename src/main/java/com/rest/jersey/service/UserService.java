package com.rest.jersey.service;

import java.sql.SQLException;
import java.util.List;

import com.rest.jersey.model.User;

public interface UserService {
    
    public List<User> getUserAll() throws ClassNotFoundException, SQLException;
    public void CreateUser(User user) throws ClassNotFoundException, SQLException;
    public boolean findByName(String name) throws ClassNotFoundException, SQLException;
    public boolean validateUser(String username, String password) throws ClassNotFoundException, SQLException;
}
