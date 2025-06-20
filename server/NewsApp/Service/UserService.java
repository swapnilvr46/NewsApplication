package com.NewsAggregation.NewsApp.Service;
import com.NewsAggregation.NewsApp.model.User;
import com.NewsAggregation.NewsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        public String registerUser(User user) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return "User already exists!";
            }
            userRepository.save(user);
            return "User registered successfully!";
        }

        public Optional<User> loginUser(String email, String password) {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent() && user.get().getPassword().equals(password)) {
                return user;
            }
            return Optional.empty();
        }
    }

