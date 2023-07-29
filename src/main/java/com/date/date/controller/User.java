package com.date.date.controller;

import com.date.date.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/dates")
public class User {
    private UserRepository repository;

//    public @GetMapping (path = "/get-all-user") List<User> getAllUser(){
//        return repository.
//    }
}
