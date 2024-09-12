package com.example.FootyFocus.auth;

import com.example.FootyFocus.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService service;

    private final UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<com.example.FootyFocus.user.User> getUserById(
            @PathVariable Long userId) {
        Optional<com.example.FootyFocus.user.User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<com.example.FootyFocus.user.User> getUserByEmail(
            @PathVariable String email) {
        Optional<com.example.FootyFocus.user.User> email1 = userRepo.findByEmail(email);
        if (email1.isPresent()) {
            return ResponseEntity.ok(email1.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
