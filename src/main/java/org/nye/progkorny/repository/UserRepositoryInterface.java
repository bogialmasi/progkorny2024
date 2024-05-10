package org.nye.progkorny.repository;

import org.nye.progkorny.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepositoryInterface {
    User getUserById(int id) throws SQLException;
    User getUserByName(String name) throws SQLException;

    boolean deleteUser(int id);
    boolean updateUser(User user);
    boolean insertUser(User user);

    List<User> getAllUser() throws SQLException;
}
