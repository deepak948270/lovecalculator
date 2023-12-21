package dev.thapak.lovecalculator.lovecalculator.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dev.thapak.lovecalculator.lovecalculator.dto.PasswordDto;
import dev.thapak.lovecalculator.lovecalculator.service.impl.UserDetailsManagerImpl;

@Controller
public class ProfileController {

    private final UserDetailsManagerImpl userDetailsManagerImpl;

    public ProfileController(UserDetailsManagerImpl userDetailsManagerImpl) {
        this.userDetailsManagerImpl = userDetailsManagerImpl;
    }

    @GetMapping("/userprofile")
    public String showUserProfile(){
        return "userprofile";
    }

    @GetMapping("/deleteaccount")
    public String deleteAccount(Principal principal){
        String userEmail = principal.getName();

        System.out.println(userEmail);
        userDetailsManagerImpl.deleteUser(userEmail);
        return "redirect:/login?accountdelected";

    }

    @GetMapping("/changepassword")
    public String showChangePassword(){
        return "changepassword";
    }

    @PostMapping("/processchangepassword")
    public String processChangePassword(PasswordDto passwordDto){

        if(passwordDto.getNewPassword().equals(passwordDto.getConfirmPassword())){
            userDetailsManagerImpl.changePassword(passwordDto.getOldPassword(),passwordDto.getNewPassword());
            return "redirect:/login?passwordchanged";
        }

        return "redirect:/login?notmatchesnewandconfirm";
    }


   
    
}
