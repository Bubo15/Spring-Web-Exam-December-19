package com.example.clothesshop.web.controller;

import com.example.clothesshop.service.model.UserLoginServiceModel;
import com.example.clothesshop.service.model.UserRegisterServiceModel;
import com.example.clothesshop.service.services.UserService;
import com.example.clothesshop.web.model.binding.UserLoginBindingModel;
import com.example.clothesshop.web.model.binding.UserRegisterBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public UserController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping("/register")
    public String getRegister(@ModelAttribute("user") UserRegisterBindingModel userRegisterBindingModel) {

        if (httpSession.getAttribute("username") != null){
            return "redirect:/";
        }

        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

        if (httpSession.getAttribute("username") != null){
            return "redirect:/";
        }

        if (model.getAttribute("username") == null) {
            model.addAttribute("username", "");
        }
        return "login";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("user") UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // redirectAttributes.addFlashAttribute("user", userRegisterBindingModel);
            return "/register";
        }

        Optional<UserRegisterServiceModel> user = this.userService.register(userRegisterBindingModel);

        if (user.isPresent()) {
            return "redirect:/user/login";
        } else {
            redirectAttributes.addFlashAttribute("ubm", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("error", "This username's already exist");
            return "redirect:/user/register";
        }
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute UserLoginBindingModel userModel,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        Optional<UserLoginServiceModel> user = this.userService.login(userModel);

        if (!user.isPresent()) {
            String errors = "Invalid username or password.";
            redirectAttributes.addFlashAttribute("username", userModel.getUsername());
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/user/login";
        } else {
            session.setAttribute("username", user.get().getUsername());
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
