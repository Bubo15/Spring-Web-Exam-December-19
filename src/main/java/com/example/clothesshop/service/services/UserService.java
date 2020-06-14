package com.example.clothesshop.service.services;

import com.example.clothesshop.service.model.UserLoginServiceModel;
import com.example.clothesshop.service.model.UserRegisterServiceModel;
import com.example.clothesshop.web.model.binding.UserLoginBindingModel;
import com.example.clothesshop.web.model.binding.UserRegisterBindingModel;

import java.util.Optional;

public interface UserService {

    Optional<UserRegisterServiceModel> register(UserRegisterBindingModel userRegisterBindingModel);

    Optional<UserLoginServiceModel> login(UserLoginBindingModel userLoginBindingModel);
}
