package org.nye.progkorny.service;

import org.nye.progkorny.model.User;

import java.util.List;

public interface UserServiceInterface {
    // C
    boolean addUser(User User);
    // R
    List<User> getAllUser();
    User getUserById(int id);
    User getUserByName(String name);
    // U
    boolean updateUser(User user);
    // D
    boolean deleteUser(int id);
}
