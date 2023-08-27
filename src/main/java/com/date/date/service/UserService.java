package com.date.date.service;

import com.date.date.model.User;
import com.date.date.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
