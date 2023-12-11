package com.bezkoder.esprit.controllers;


import com.bezkoder.esprit.models.User;
import com.bezkoder.esprit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")

    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}