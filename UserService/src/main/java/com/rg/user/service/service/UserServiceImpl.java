package com.rg.user.service.service;

import com.rg.user.service.entity.User;
import com.rg.user.service.exception.ResourceNotFoundException;
import com.rg.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;
    @Override
    public User saveUser(User user) {
        String randomID = UUID.randomUUID().toString();
        user.setUserId(randomID);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
    }
}
