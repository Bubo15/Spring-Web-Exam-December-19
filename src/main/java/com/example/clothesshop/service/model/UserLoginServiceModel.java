package com.example.clothesshop.service.model;

import com.example.clothesshop.data.entities.Role;

import java.util.Set;

public class UserLoginServiceModel {

    private long id;
    private String username;
    private String password;
    private Set<Role> roles;


    public UserLoginServiceModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
