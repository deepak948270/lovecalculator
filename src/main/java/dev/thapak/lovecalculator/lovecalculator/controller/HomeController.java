package dev.thapak.lovecalculator.lovecalculator.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Authentication authentication,Principal principal,Model model){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String name = principal.getName();
        model.addAttribute("name", name);
        model.addAttribute("roles", authorities);

        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }
    
}
