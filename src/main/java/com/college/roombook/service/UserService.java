package com.college.roombook.service;

import com.college.roombook.entity.User;
import com.college.roombook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}