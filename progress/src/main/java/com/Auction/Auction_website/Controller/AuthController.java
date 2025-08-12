package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Entity.User;
import com.Auction.Auction_website.Repository.User_Repo;
import com.Auction.Auction_website.Requests.LoginRequest;
import com.Auction.Auction_website.Jwt.JwtUtil;
import com.Auction.Auction_website.Service.Impl.UserServiceImpl;
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
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserServiceImpl user_serv;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if email already exists
        if (user_repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("❌ Email already exists.Try to Login.");
        }
        user_serv.register(user);
        return ResponseEntity.ok("✅ User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
        System.out.println(req.getEmail());
     User user=user_repo.findByEmail(req.getEmail()).orElseThrow(()-> new RuntimeException("❌ User not found"));
   if(!passwordEncoder.matches(req.getPassword(), user.getPassword()))
       return ResponseEntity.badRequest().body("❌ Invalid credentials");
        // 🔐 Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());

        // ✅ Return token + user info
        Map<String, Object> response = new HashMap<>();
        response.put("message", "✅ Login successful");
        response.put("token", token);
        response.put("userId", user.getId());
        response.put("name", user.getName());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }

}
