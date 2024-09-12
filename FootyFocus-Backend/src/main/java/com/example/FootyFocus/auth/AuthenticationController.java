package com.example.FootyFocus.auth;

import com.example.FootyFocus.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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

//    @PutMapping("/user/update/{email}")
//    public ResponseEntity<?> updateUser(
//            @PathVariable String email,
//            @RequestBody UpdateUserRequest updateUserRequest) {
//
//        // Fetch user by email
//        Optional<com.example.FootyFocus.user.User> userOptional = userRepo.findByEmail(email);
//
//        // Check if the user exists
//        if (userOptional.isPresent()) {
//            com.example.FootyFocus.user.User user = userOptional.get();
//
//            // Update user details
//            user.setFirstname(updateUserRequest.getFirstName());
//            user.setLastname(updateUserRequest.getLastName());
//            user.setFavTeam(updateUserRequest.getFavTeam());
//            user.setBiography(updateUserRequest.getBiography());
//
//            // Save updated user to the repository
//            userRepo.save(user);
//
//            // Return success response
//            return ResponseEntity.ok("User updated successfully");
//        } else {
//            // If user not found, return 404 response
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }

    @PutMapping("/user/update/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable String email,
            @RequestBody UpdateUserRequest updateUserRequest) {

        // Fetch user by email
        Optional<com.example.FootyFocus.user.User> userOptional = userRepo.findByEmail(email);

        // Check if the user exists
        if (userOptional.isPresent()) {
            com.example.FootyFocus.user.User user = userOptional.get();

            // Update user details
            user.setFirstname(updateUserRequest.getFirstName());
            user.setLastname(updateUserRequest.getLastName());
            user.setFavTeam(updateUserRequest.getFavTeam());
            user.setBiography(updateUserRequest.getBiography());

            // Save updated user to the repository
            userRepo.save(user);

            // Return success response as a JSON object
            Map<String, String> response = new HashMap<>();
            response.put("message", "User updated successfully");
            return ResponseEntity.ok(response);
        } else {
            // If user not found, return 404 response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }




}
