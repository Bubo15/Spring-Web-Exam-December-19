package com.example.clothesshop.web.controller;

import com.example.clothesshop.service.services.ProductService;
import com.example.clothesshop.web.model.view.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getHome(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "index";
        }

        List<ProductViewModel> productViewModels = this.productService
                .getAllProducts()
                .stream()
                .map(productServiceModel -> this.modelMapper.map(productServiceModel, ProductViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("products", productViewModels);
        return "home";
    }

}
