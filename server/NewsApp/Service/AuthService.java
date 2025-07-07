package com.NewsAggregation.NewsApp.service;
import com.NewsAggregation.NewsApp.dto.LoginDTO;
import com.NewsAggregation.NewsApp.dto.UserDTO;
import com.NewsAggregation.NewsApp.model.User;
import com.NewsAggregation.NewsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public String signup(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isBlank()) {
            return "Username is required.";
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isBlank()) {
            return "Email is required.";
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().trim().isBlank()) {
            return "Password is required.";
        }

        if (userDTO.getRole() == null || userDTO.getRole().trim().isBlank()) {
            return "Role is required.";
        }
        String role = userDTO.getRole().toUpperCase();
        if (!role.equals("USER") && !role.equals("ADMIN")) {
            return "Invalid role! Only USER or ADMIN are allowed.";
        }
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists!";
        }
        try {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setRole(role);
            userRepository.save(user);
            return "Signup successful!";
        } catch (Exception e){
            System.out.println("Database error during signup: " + e.getMessage());
            return "Signup failed due to a server/database error. Please try again.";
        }
    }
    public String login(LoginDTO loginDTO) {
       if (loginDTO.getEmail() == null || loginDTO.getEmail().trim().isBlank()) {
        return "Email is required.";
    }
    if (loginDTO.getPassword() == null || loginDTO.getPassword().trim().isBlank()) {
        return "Password is required.";
    }
    Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());
    if (userOpt.isEmpty()) {
        return "User not found.";
    }
    User user = userOpt.get();
    if (!user.getPassword().equals(loginDTO.getPassword())) {
        return "Invalid password.";
    }
    return "Login successful!";
}
}
