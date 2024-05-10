package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.EventType;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository extends GenericDataAccess<User> implements UserRepositoryInterface {

    // C

    @Override
    public boolean insertUser(User user) {
        int rowsAffected = upsert("INSERT INTO user " +
                "(name) VALUES" + user.getName()+ ";");
        return rowsAffected == 1;
    }

    // R

    @Override
    public User getUserById(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM user WHERE id = %d;", id);
        return query(sqlQuery).get(0);
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM user WHERE name = '%s';", name);
        return query(sqlQuery).get(0);
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String sqlQuery = "SELECT * FROM user;";
        return query(sqlQuery);
    }

    // U

    @Override
    public boolean updateUser(User user) {
        int rowsAffected = upsert("UPDATE user SET name = " + user.getName() + ";");
        return rowsAffected == 1;
    }

    // D
    @Override
    public boolean deleteUser(int id) {
        String sqlQuery = String.format("DELETE FROM user WHERE id = %d", id);
        return (delete(sqlQuery));
    }

    @Override
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
