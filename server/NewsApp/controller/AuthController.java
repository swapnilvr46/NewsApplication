package com.NewsAggregation.NewsApp.controller;
import com.NewsAggregation.NewsApp.dto.LoginDTO;
import com.NewsAggregation.NewsApp.dto.UserDTO;
import com.NewsAggregation.NewsApp.response.ApiResponse;
import com.NewsAggregation.NewsApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody UserDTO userDTO) {
        String result = authService.signup(userDTO);
        if (result.equals("User already exists!")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Duplicate",409, result)); //  duplicate signup
        }
        if (result.startsWith("Invalid role") || result.endsWith("is required.")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid",400,result));
        }
        if (result.startsWith("Signup failed due to a server/database error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", 500, result));
        }
        return ResponseEntity
                .status(HttpStatus.OK).body(new ApiResponse("Success",200,result)); //  for successfully signup
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {
        String result = authService.login(loginDTO);
        if (result.equals("User not found.") || result.equals("Invalid password.")
                || result.endsWith("is required.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Denied", 400, result));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("success", 200, result));
    }
 }
