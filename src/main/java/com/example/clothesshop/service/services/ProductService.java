package com.example.clothesshop.service.services;

import com.example.clothesshop.service.model.ProductServiceModel;
import com.example.clothesshop.web.model.binding.ProductAddBindingModel;

import java.util.List;

public interface ProductService {

    List<ProductServiceModel> getAllProducts();

    ProductServiceModel add(ProductAddBindingModel productAddBindingModel);

    ProductServiceModel getProductById(long id);


    void removeById(long id);
}
