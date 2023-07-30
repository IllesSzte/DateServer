package com.date.date.controller;

import com.date.date.model.User;
import com.date.date.repository.UserRepository;
import com.date.date.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(path = "/get-all-user")
    List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(path = "/get")
    User get() {
        return userService.get();
    }
}
