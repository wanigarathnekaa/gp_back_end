package com.example.gp_back_end.repository;

import com.example.gp_back_end.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface userRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
