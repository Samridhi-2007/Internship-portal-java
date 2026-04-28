package com.internship.internshipmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.internship.internshipmanagement.model.User;
import com.internship.internshipmanagement.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        user.setEmail(user.getEmail() == null ? null : user.getEmail().trim().toLowerCase());

        Optional<User> existingUser = resolveExistingUser(user);
        String password = user.getPassword();

        if (password == null || password.isBlank()) {
            if (existingUser.isPresent()) {
                user.setPassword(existingUser.get().getPassword());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
            }
        } else if (!isEncoded(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private boolean isEncoded(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }

    private Optional<User> resolveExistingUser(User user) {
        if (user.getId() != null) {
            Optional<User> byId = userRepository.findById(user.getId());
            if (byId.isPresent()) {
                return byId;
            }
        }

        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            return userRepository.findByEmail(user.getEmail());
        }

        return Optional.empty();
    }
}
