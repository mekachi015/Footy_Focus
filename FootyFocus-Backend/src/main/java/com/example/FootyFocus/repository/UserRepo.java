package com.example.FootyFocus.repository;

import com.example.FootyFocus.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo {

    Optional<Object> findByEmail(String email);

//    public interface UserRepository extends JpaRepository<User, Integer> {
//        Optional<User> findByEmail(String email);
//    }
}
