package com.rest.jersey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "user")
@XmlRootElement
public class User {
    
    @Id
    @GeneratedValue
    private int iduser;
    private String name;
    private String password;
    private boolean enabled;
    
    
	public User() {}
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public int getIduser() {
        return iduser;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
