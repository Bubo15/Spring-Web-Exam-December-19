package com.example.clothesshop.web.controller;

import com.example.clothesshop.service.services.ProductService;
import com.example.clothesshop.web.model.binding.ProductAddBindingModel;
import com.example.clothesshop.web.model.view.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, HttpSession httpSession) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String getProduct(@ModelAttribute("product") ProductAddBindingModel productAddBindingModel) {

        if (httpSession.getAttribute("username") == null){
            return "redirect:/user/login";
        }
        return "add-product";
    }

    @PostMapping("/add")
    public String postProduct(@Valid @ModelAttribute("product") ProductAddBindingModel productAddBindingModel,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", productAddBindingModel);
            return "/add";
        }

        this.productService.add(productAddBindingModel, session.getAttribute("username").toString());
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable long id, Model model){
        if (httpSession.getAttribute("username") == null){
            return "redirect:/user/login";
        }

        ProductViewModel productViewModel = this.modelMapper.map(productService.getProductById(id), ProductViewModel.class);
        model.addAttribute("product", productViewModel);

        return "details-product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){

        if (httpSession.getAttribute("username") == null){
            return "redirect:/user/login";
        }

        this.productService.removeById(id);
        return "redirect:/";
    }

}
