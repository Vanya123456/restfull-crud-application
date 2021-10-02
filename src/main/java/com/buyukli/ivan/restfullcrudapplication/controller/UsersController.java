package com.buyukli.ivan.restfullcrudapplication.controller;

import com.buyukli.ivan.restfullcrudapplication.entity.User;
import com.buyukli.ivan.restfullcrudapplication.exception.ResourceNotFoundException;
import com.buyukli.ivan.restfullcrudapplication.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private UsersRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createEmployee(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployeeById(@PathVariable(value = "id") long id
            , @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName((userDetails.getLastName()));
        user.setEmail((userDetails.getEmail()));
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") long id) throws ResourceNotFoundException{
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

