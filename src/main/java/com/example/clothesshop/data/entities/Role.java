package com.example.clothesshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class Role extends BaseEntity {

    private String authority;

    public Role() {
    }

    @Column(name = "authority", nullable = false)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
