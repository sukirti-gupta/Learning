package com.rest.jersey.dao;

import java.sql.SQLException;
import java.util.List;

import com.rest.jersey.model.User;

public interface UserDAO {
    
    public List<User> getUserAll() throws ClassNotFoundException, SQLException;
    public void CreateUser(User user) throws ClassNotFoundException, SQLException;
    public List<User> findByName(String name) throws ClassNotFoundException, SQLException;
}
