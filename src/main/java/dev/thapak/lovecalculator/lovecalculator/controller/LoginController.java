package dev.thapak.lovecalculator.lovecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dev.thapak.lovecalculator.lovecalculator.entity.User;
import dev.thapak.lovecalculator.lovecalculator.service.impl.UserDetailsManagerImpl;

@Controller
public class LoginController {

    private final UserDetailsManagerImpl userDetailsManagerImpl;

    public LoginController(UserDetailsManagerImpl userDetailsManagerImpl) {
        this.userDetailsManagerImpl = userDetailsManagerImpl;
    }

    // show login form
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // show signup form
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // signup sucess
    @PostMapping("/signupsucess")
    public String signupsucess(User user) {

        // call to service layer to save user
        // userService.saveUser(user);

        // call to UserDetailsManagerImpl
        userDetailsManagerImpl.createUser(user);
        return "redirect:/login?signupSuccess=true";
    }

}
