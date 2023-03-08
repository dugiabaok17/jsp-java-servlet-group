package com.example.demo2.service.impl;

import com.example.demo2.model.User;
import com.example.demo2.repositories.UserRepositories;
import com.example.demo2.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepositories userRepositories;

    public UserServiceImpl( ) {
        userRepositories =  new UserRepositories();
    }

    @Override
    public List<User> listUser() {
        return userRepositories.getAllUser();
    }

    @Override
    public void insertOrUpdateUser(User user) {
        userRepositories.saveOrUpdateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepositories.deleteUser(id);
    }

    @Override
    public User getUserById(int id) {
        return userRepositories.getUserById(id);
    }
}
