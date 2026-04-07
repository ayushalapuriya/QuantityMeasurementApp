package com.app.quantitymeasurement.security.user;

import com.app.quantitymeasurement.dto.AuthRequest;
import com.app.quantitymeasurement.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*") // optional (helps frontend testing)
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            JwtUtil jwtUtil,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {

        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists ❌");
        }

        UserEntity user = new UserEntity(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                "USER"
        );

        userRepository.save(user);

        return ResponseEntity.ok("User Registered Successfully ✅");
    }

    // ✅ LOGIN USER
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        UserEntity user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found ❌"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password ❌");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(token);
    }
}