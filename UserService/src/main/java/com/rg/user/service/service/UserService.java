package com.rg.user.service.service;

import com.rg.user.service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
}
