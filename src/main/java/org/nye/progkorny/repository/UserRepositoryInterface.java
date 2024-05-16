package org.nye.progkorny.repository;

import org.nye.progkorny.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepositoryInterface {
    User getUserById(int id) throws SQLException;
    User getUserByName(String name) throws SQLException;

    boolean deleteUser(int id) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean insertUser(User user) throws SQLException;

    List<User> getAllUser() throws SQLException;
}
