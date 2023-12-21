package dev.thapak.lovecalculator.lovecalculator.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.thapak.lovecalculator.lovecalculator.entity.User;
import dev.thapak.lovecalculator.lovecalculator.repository.UserRepository;
import dev.thapak.lovecalculator.lovecalculator.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {

        if (user != null) {

            // create unique user id for each user
            String userId = UUID.randomUUID().toString();

            User modifiedUser = User.builder()
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(List.of("ROLE_USER"))
                    .name(user.getName())
                    .id(userId).build();
            
            // logging modified user
            log.info(modifiedUser.toString());

            // call repository layer to save user
            return userRepository.save(modifiedUser);
        }
        return null;

    }

    @Override
    public List<User> getUsers() {

        // call to repository layer to find all user's
        return userRepository.findAll();
    }

}
