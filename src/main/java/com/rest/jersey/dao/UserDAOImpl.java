package com.rest.jersey.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.jersey.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final static Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    private static final String GET_USER="from User";
    
    @SuppressWarnings("unchecked")
    public List<User> getUserAll() throws ClassNotFoundException, SQLException {
        LOG.debug("Getting all users");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(GET_USER);
        return query.list();
    }
    
    public void CreateUser(User user) throws SQLException, ClassNotFoundException {
        LOG.debug("Creating user");
        Session session = sessionFactory.getCurrentSession();
        User user2 = new User();
        user2.setName(user.getName());
        user2.setPassword(user.getPassword());
        session.persist(user2);
    }
    
    @SuppressWarnings("unchecked")
    public List<User> findByName(String name) {
        LOG.debug("Finding by username");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(GET_USER+" where name = '"+name+"'");
        return query.list();
    }

}
