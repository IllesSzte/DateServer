package com.date.date.controller;

import com.date.date.model.User;
import com.date.date.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/get-all-user")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(path = "/create")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(path = "/get-user-by-id")
    public Optional<User> getUser(@RequestParam int id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/get-by-name-and-password")
    public Optional<User> getUserByName(@RequestParam String name, String password) {
        return userService.getUserByNameAndPassword(name, password);
    }
}
