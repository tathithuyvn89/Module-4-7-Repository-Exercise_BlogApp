package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    private String showRegistrationForm(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }
    @PostMapping("/register")
    private String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "user/register";
        } else {
            userService.saveUser(user);
            return "user/registerSuccess";
        }

    }
}
