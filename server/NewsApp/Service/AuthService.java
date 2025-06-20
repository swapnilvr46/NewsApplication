package com.NewsAggregation.NewsApp.Service;
import com.NewsAggregation.NewsApp.dto.UserDTO;
import com.NewsAggregation.NewsApp.model.User;
import com.NewsAggregation.NewsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signup(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists!";
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return "Signup successful!";
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return "Invalid email or password";

        User user = userOpt.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful as " + user.getRole();
        } else {
            return "Invalid email or password";
        }
    }
}
