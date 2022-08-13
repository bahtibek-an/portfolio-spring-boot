package com.example.portfolio.service;

import com.example.portfolio.entity.UserEntity;
import com.example.portfolio.model.User;
import com.example.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registration(UserEntity user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("User with this username already exists!");
        }
        return userRepository.save(user);
    }

    public User getOne(Long id) {
        UserEntity user = userRepository.findById(id).get();

        return User.toModel(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
