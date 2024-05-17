package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.GenericDataAccessInterface;
import org.nye.progkorny.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {

    @Autowired
    GenericDataAccessInterface dac;

    public UserRepository(GenericDataAccessInterface dac) {
        this.dac = dac;
    }

    @Override
    public boolean insertUser(User user) throws SQLException {
        int rowsAffected = dac.upsert("INSERT INTO user " +
                "(name) VALUES ('" + user.getName() + "');");
        return rowsAffected == 1;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM user WHERE id = %d;", id);
        return map(dac.query(sqlQuery)).get(0);
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM user WHERE name = '%s';", name);
        return map(dac.query(sqlQuery)).get(0);
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String sqlQuery = "SELECT * FROM user;";
        return map(dac.query(sqlQuery));
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        int rowsAffected = dac.upsert("UPDATE user SET name = '" + user.getName() + "' WHERE id = " + user.getId() + " ;");
        return rowsAffected == 1;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        String sqlQuery = String.format("DELETE FROM user WHERE id = %d", id);
        return (dac.delete(sqlQuery));
    }

    List<User> map(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            users.add(new User(id, name));
        }
        return users;
    }
}
