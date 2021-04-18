package com.github.PopovDmitry.nstu.webcw.service;

import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        logger.info("saveUser");
        userRepository.save(user);
    }

    public Optional<User> getUser(long id) {
        logger.info("getUser with id {}", id);
        return userRepository.findById(id);
    }

    public Optional<User> getUser(String email) {
        logger.info("getUser with email {}", email);
        return userRepository.findByEmail(email);
    }

    public void updateUser(User user) {
        logger.info("updateUser");
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        logger.info("deleteUser");
        userRepository.delete(user);
    }
}
