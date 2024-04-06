package com.date.date.repository;

import com.date.date.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserNameAndPassword(String name, String password);

    Optional<User> findByUserName(String name);
    User findUserById(int id);

}