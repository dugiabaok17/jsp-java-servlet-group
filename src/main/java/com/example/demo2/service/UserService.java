package com.example.demo2.service;

import com.example.demo2.model.User;

import java.util.List;

public interface UserService {
    List<User> listUser();

    void insertOrUpdateUser(User user);

    void deleteUser(int id);

    User getUserById(int id);


}
