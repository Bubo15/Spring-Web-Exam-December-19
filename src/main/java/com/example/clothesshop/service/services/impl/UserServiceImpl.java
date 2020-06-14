package com.example.clothesshop.service.services.impl;

import com.example.clothesshop.data.entities.Role;
import com.example.clothesshop.data.entities.User;
import com.example.clothesshop.data.repositories.UserRepository;
import com.example.clothesshop.service.model.UserLoginServiceModel;
import com.example.clothesshop.service.model.UserRegisterServiceModel;
import com.example.clothesshop.service.services.UserService;
import com.example.clothesshop.web.model.binding.UserLoginBindingModel;
import com.example.clothesshop.web.model.binding.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<UserRegisterServiceModel> register(UserRegisterBindingModel userModel) {

        if (this.userRepository.getByUsername(userModel.getUsername()) != null) {
            return Optional.empty();
        }

        User user = this.modelMapper.map(userModel, User.class);

     //   user.setPassword(this.bCryptPasswordEncoder.encode(userModel.getPassword()));

        Role role = new Role();
        if (userRepository.findAll().size() == 0) {
            role.setAuthority("ADMIN");
        } else {
            role.setAuthority("BUYER");
        }

        user.setAuthorities(new HashSet<>(Set.of(role)));
        this.userRepository.saveAndFlush(user);

        return Optional.of(this.modelMapper.map(user, UserRegisterServiceModel.class));
    }

    @Override
    public Optional<UserLoginServiceModel> login(UserLoginBindingModel userModel) {
        User user = this.userRepository.getByUsername(userModel.getUsername());

        if (user == null) {
            return Optional.empty();
        }

//        if(this.bCryptPasswordEncoder.matches(user.getPassword(), userModel.getPassword())){
//            return Optional.empty();
//        }

        if(!user.getPassword().equals(userModel.getPassword())){
            return Optional.empty();
        }

        return Optional.of(this.modelMapper.map(user, UserLoginServiceModel.class));
    }
}
