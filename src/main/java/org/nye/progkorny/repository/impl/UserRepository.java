package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {
    @Override
    public List<User> getUser() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User deleteUser(int id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User insertUser(User user) {
        return null;
    }
}
