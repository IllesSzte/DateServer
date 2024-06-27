package com.date.date.controller;

import com.date.date.exceptions.UserExceptions;
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
    public User getUser(@RequestParam int id) throws UserExceptions {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/get-by-name-and-password")
    public Optional<User> getUserByNameAndPassword(@RequestParam String name, @RequestParam String password) {
        return userService.getUserByNameAndPassword(name, password);
    }

    @GetMapping(path = "/get-by-name")
    public Optional<User> getUserByNameAndPassword(@RequestParam String name) {
        return userService.getUserByName(name);
    }

    @DeleteMapping(path = "/delete-user")
    private void delete(@RequestParam int userId) {
         userService.deleteUser(userId);
    }
}
