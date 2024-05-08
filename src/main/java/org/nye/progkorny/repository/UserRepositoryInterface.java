package org.nye.progkorny.repository;

import org.nye.progkorny.model.User;

import java.util.List;

public interface UserRepositoryInterface {
    List<User> getUser();
    User getUserById(int id);
    User getUserByName(String name);

    User deleteUser(int id);
    User updateUser(User user);
    User insertUser(User user);
}
