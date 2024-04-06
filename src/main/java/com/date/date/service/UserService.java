package com.date.date.service;

import com.date.date.exceptions.UserExceptions;
import com.date.date.model.User;
import com.date.date.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.date.date.exceptions.ExceptionMesageEnum.USER_HAS_NO_PARTNER;
import static com.date.date.exceptions.ExceptionMesageEnum.USER_NOT_FOUND;

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

    public User getUserById(int id) throws UserExceptions {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserExceptions(USER_NOT_FOUND));
    }

    public Optional<User> getUserByNameAndPassword(String name, String password) {
        return userRepository.findByUserNameAndPassword(name, password);
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    public User getPartner(User user) throws UserExceptions {
        if(hasPartner(user)){
         return   getUserById(user.getPartnerId());
        }
        throw new UserExceptions(USER_HAS_NO_PARTNER);
    }
    private boolean hasPartner(User user){
        return user.getPartnerId()!=0;
    }

}
