package de.developerpat.flywayjpademo.controller;

import de.developerpat.flywayjpademo.domain.User;
import de.developerpat.flywayjpademo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class UserController {

    public Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity getUserById(@PathVariable int userid) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/users")
    public ResponseEntity setUser(@RequestBody User user) {
        return ResponseEntity.created(URI.create("http://localhost:8080/users/" + userRepository.save(user).getId()))
                .build();
    }
}
