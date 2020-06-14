package com.example.clothesshop.service.services.impl;

import com.example.clothesshop.data.entities.Product;
import com.example.clothesshop.data.entities.User;
import com.example.clothesshop.data.repositories.ProductRepository;
import com.example.clothesshop.service.model.ProductServiceModel;
import com.example.clothesshop.service.services.ProductService;
import com.example.clothesshop.service.services.UserService;
import com.example.clothesshop.web.model.binding.ProductAddBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductServiceModel> getAllProducts() {
        return this.productRepository
                .findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel add(ProductAddBindingModel productAddBindingModel, String username) {
        Product product = this.modelMapper.map(productAddBindingModel, Product.class);

        User user = this.userService.getUserByUsername(username);

        product.setUser(user);

        this.productRepository.saveAndFlush(product);

        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel getProductById(long id) {
        return this.modelMapper.map(this.productRepository.getProductById(id), ProductServiceModel.class);
    }

    @Override
    public void removeById(long id) {
        this.productRepository.deleteById(id);
    }
}
