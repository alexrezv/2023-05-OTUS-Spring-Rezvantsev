package com.alexrezv.service.impl;

import com.alexrezv.domain.User;
import com.alexrezv.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User build(String firstName, String lastName) {
        return new User(firstName, lastName);
    }

}
