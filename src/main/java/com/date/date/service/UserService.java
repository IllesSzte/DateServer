package com.date.date.service;

import com.date.date.model.User;
import com.date.date.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User get() {
        User user = new User();
        user.setId(2);
        return userRepository.save(user);
    }
}
