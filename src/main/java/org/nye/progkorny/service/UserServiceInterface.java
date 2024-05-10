package org.nye.progkorny.service;

import org.nye.progkorny.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {
    // C
    boolean addUser(User User);
    // R
    List<User> getAllUser() throws SQLException;
    User getUserById(int id) throws SQLException;
    User getUserByName(String name) throws SQLException;
    // U
    boolean updateUser(User user);
    // D
    boolean deleteUser(int id);
}
