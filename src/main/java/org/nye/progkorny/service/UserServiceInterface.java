package org.nye.progkorny.service;

import org.nye.progkorny.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {
    boolean addUser(User User) throws SQLException;

    List<User> getAllUser() throws SQLException;

    User getUserById(int id) throws SQLException;

    User getUserByName(String name) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUser(int id) throws SQLException;
}
