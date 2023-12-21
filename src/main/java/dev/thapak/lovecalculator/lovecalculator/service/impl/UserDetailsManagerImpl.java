package dev.thapak.lovecalculator.lovecalculator.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import dev.thapak.lovecalculator.lovecalculator.entity.User;
import dev.thapak.lovecalculator.lovecalculator.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsManagerImpl implements UserDetailsManager {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDetailsManagerImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("inside loadUserByUsername() method of UserDetailsManager");

        if (username != null) {
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
        }

        return null;

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // getting the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = (User) loadUserByUsername(email);

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {

            // setting the new password to the user
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            System.out.println("password change sucessfully");

        } else {
            throw new BadCredentialsException(" invalid old password !!");
        }

    }

    @Override
    public void createUser(UserDetails user) {

        User myUser = (User) user;

       System.out.println("inside create user of UserDetailsManagerImpl");

        // create a Unique user id for each user
        String userId = UUID.randomUUID().toString();

        User modifiedUser = User.builder()
                .id(userId)
                .name(myUser.getName())
                .email(myUser.getUsername())
                .password(passwordEncoder.encode(myUser.getPassword()))
                .roles(List.of("Role_User"))
                .build();

        // log the modified user
        log.info(modifiedUser.toString());

        // save modified user to db
        userRepository.save(modifiedUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = (User) loadUserByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }

    }

    @Override
    public void updateUser(UserDetails user) {

        userRepository.save((User) user);

    }

    @Override
    public boolean userExists(String username) {
        Optional<User> optionalOfUser = userRepository.findByEmail(username);
        if (optionalOfUser.isPresent()) {
            log.info(optionalOfUser.get().toString());
            return true;
        }
        return false;

    }

}
