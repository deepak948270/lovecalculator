package dev.thapak.lovecalculator.lovecalculator.service;

import java.util.List;

import dev.thapak.lovecalculator.lovecalculator.entity.User;

public interface UserService {

    // save User to db 
    User saveUser(User user);

    // find all User's
    List<User> getUsers();
    
}
