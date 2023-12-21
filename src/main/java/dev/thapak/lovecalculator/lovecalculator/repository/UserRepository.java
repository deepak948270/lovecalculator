package dev.thapak.lovecalculator.lovecalculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.thapak.lovecalculator.lovecalculator.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // find User based on email
    Optional<User> findByEmail(String email);

}
