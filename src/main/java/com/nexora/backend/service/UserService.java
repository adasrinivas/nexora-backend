package com.nexora.backend.service;

import com.nexora.backend.dto.UserResponse;
import com.nexora.backend.exception.EmailAlreadyExistsException;
import com.nexora.backend.exception.InvalidCredentialsException;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }

        String normalizedEmail = user.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new EmailAlreadyExistsException("An account with this email already exists.");
        }

        user.setName(user.getName().trim());
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public UserResponse loginUser(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidCredentialsException("Email and password are required");
        }

        String normalizedEmail = email.trim();

        User user = userRepository.findByEmailIgnoreCase(normalizedEmail)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        String storedPassword = user.getPassword();
        boolean passwordMatches = false;

        if (storedPassword != null && isBCryptHash(storedPassword)) {
            passwordMatches = passwordEncoder.matches(password, storedPassword);
        } else if (storedPassword != null && storedPassword.equals(password)) {
            // Legacy user with plain-text password: authenticate and upgrade to BCrypt
            passwordMatches = true;
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    private boolean isBCryptHash(String password) {
        return (password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$"))
                && password.length() == 60;
    }
}
