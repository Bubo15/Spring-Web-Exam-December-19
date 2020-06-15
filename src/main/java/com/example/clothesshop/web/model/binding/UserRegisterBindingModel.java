package com.example.clothesshop.web.model.binding;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Email;

@ScriptAssert(lang = "javascript",
        script = "_this.confirmPassword !== null &&_this.password === _this.confirmPassword",
        reportOn = "confirmPassword",
        message = "Passwords don't match")
public class UserRegisterBindingModel {

    private long id;
    private String username;
    private String password;
    private String email;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Length(min = 2, message = "Username must be least 2 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 5, message = "Password must be least 5 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
