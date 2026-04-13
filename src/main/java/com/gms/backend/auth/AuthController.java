/*
package com.gms.backend.auth;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.CustomerRepository;
import com.gms.backend.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(CustomerRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> data) {

        String email = data.get("email");
        String password = data.get("password");

        Customer user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 DEBUG (VERY IMPORTANT)
        System.out.println("INPUT PASSWORD: " + password);
        System.out.println("DB PASSWORD: " + user.getPassword());
        System.out.println("MATCH: " + encoder.matches(password, user.getPassword()));

        // ✅ SINGLE CHECK ONLY
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(email);

        return Map.of("token", token);
    }
    }
*/
