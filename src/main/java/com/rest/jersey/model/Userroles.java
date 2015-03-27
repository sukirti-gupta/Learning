package com.rest.jersey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "userroles")
@XmlRootElement
public class Userroles {
	
	@Id
    @GeneratedValue
    private int iduserroles;
	private String name;
    private String role;
    
    public int getIduserroles() {
		return iduserroles;
	}
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
