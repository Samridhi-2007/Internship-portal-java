package com.internship.internshipmanagement.service;

import com.internship.internshipmanagement.dto.AuthResponse;
import com.internship.internshipmanagement.dto.LoginRequest;
import com.internship.internshipmanagement.dto.RegisterRequest;
import com.internship.internshipmanagement.dto.UserResponse;
import com.internship.internshipmanagement.model.Role;
import com.internship.internshipmanagement.model.User;
import com.internship.internshipmanagement.repository.UserRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest req) {
        String name = safe(req.getName()).trim();
        String email = normalizeEmail(req.getEmail());
        String password = safe(req.getPassword());
        String roleStr = safe(req.getRole());

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required fields");
        }

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        // Convert role string to enum safely
        user.setRole(parseRole(roleStr));

        try {
            User saved = userRepository.save(user);
            return new AuthResponse(null, toUserResponse(saved));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
    }

    public AuthResponse login(LoginRequest req) {
        String email = normalizeEmail(req.getEmail());
        String password = safe(req.getPassword());

        if (email.isBlank() || password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and password are required");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        String storedPassword = safe(user.getPassword());
        if (!passwordEncoder.matches(password, storedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        // Check role if provided
        String roleStr = safe(req.getRole());
        if (!roleStr.isBlank()) {
            Role incomingRole = parseRole(roleStr);
            if (incomingRole != user.getRole()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Role mismatch");
            }
        }

        return new AuthResponse(null, toUserResponse(user));
    }

    private UserResponse toUserResponse(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole().name());
    }

    // Converts string to Role enum safely
    private Role parseRole(String role) {
        if (role == null || role.isBlank()) {
            return Role.USER; // default role
        }
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role: " + role);
        }
    }

    private String normalizeEmail(String email) {
        return safe(email).trim().toLowerCase();
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}