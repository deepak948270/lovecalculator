package dev.thapak.lovecalculator.lovecalculator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thapak.lovecalculator.lovecalculator.entity.User;
import dev.thapak.lovecalculator.lovecalculator.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all user's
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        // make service call to retive user's
        List<User> retrivedUsers = userService.getUsers();

        return new ResponseEntity<>(retrivedUsers, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        // call to service call to save user
        User persitedUser = userService.saveUser(user);
        return new ResponseEntity<>(persitedUser, HttpStatus.CREATED);
    }

}
