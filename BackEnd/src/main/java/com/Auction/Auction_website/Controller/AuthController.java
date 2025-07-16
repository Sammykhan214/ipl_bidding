package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Entity.User;
import com.Auction.Auction_website.Repository.User_Repo;
import com.Auction.Auction_website.Requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auction/auth")
public class AuthController {
    @Autowired
    private User_Repo user_repo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if email already exists
        if (user_repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("❌ Email already exists");
        }
        // Save user (plain password for now)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user_repo.save(user);
        return ResponseEntity.ok("✅ User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
     User user=user_repo.findByEmail(req.getEmail()).orElseThrow(()-> new RuntimeException("❌ User not found"));
   if(!passwordEncoder.matches(req.getPassword(), user.getPassword()))
       return ResponseEntity.badRequest().body("❌ Invalid credentials");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "✅ Login successful");
        response.put("userId", user.getId());
        response.put("name", user.getName());
        response.put("role", user.getRole());
        return ResponseEntity.ok(response);
    }

}
