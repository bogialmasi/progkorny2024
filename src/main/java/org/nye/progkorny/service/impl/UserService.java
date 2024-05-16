package org.nye.progkorny.service.impl;

import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.UserRepositoryInterface;
import org.nye.progkorny.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        return userRepository.insertUser(user);
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        return userRepository.getAllUser();
    }

    @Override
    public User getUserById(int id) throws SQLException {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        return userRepository.getUserByName(name);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return userRepository.deleteUser(id);
    }
}
