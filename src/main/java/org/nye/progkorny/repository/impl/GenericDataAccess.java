package org.nye.progkorny.repository.impl;

import org.nye.progkorny.repository.GenericDataAccessInterface;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.List;

public abstract class GenericDataAccess<T> implements GenericDataAccessInterface<T> {

    @Value("${spring.datasource.url}")
    public String dbUrl;
    @Value("${spring.datasource.username}")
    public String user;
    @Value("${spring.datasource.password}")
    public String pw;

    abstract List<T> map(ResultSet resultSet) throws SQLException;

    @Override
    public List<T> query(String sqlQuery) throws SQLException {
        ResultSet resultSet;
        try(Connection connection = DriverManager.getConnection(dbUrl, user, pw)){
            resultSet = queryImpl(connection, sqlQuery);
            return map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("An exception occured when QUERYING from database: " + e.getMessage());
        }
    }
    private ResultSet queryImpl(Connection connection, String sqlQuery) throws SQLException {
        return createStatement(connection).executeQuery(sqlQuery);
    }

    public boolean delete(String sqlQuery){
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pw)){
            if(! deleteImpl(connection, sqlQuery)){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("An exception occured when DELETING a row from database: " + e.getMessage());
        }
        return false;
    }
    private boolean deleteImpl(Connection connection, String sqlQuery) throws SQLException {
        return createStatement(connection).execute(sqlQuery);
        // execute() returns false if no results
    }

    public int upsert(String sqlQuery) {
        try(Connection connection = DriverManager.getConnection(dbUrl, user, pw)){
            return upsertImpl(connection, sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException("An exception occured when INSERTING OR UPDATING a row from database: " + e.getMessage());
        }
    }
    private int upsertImpl(Connection connection, String sqlQuery) throws SQLException {
        return createStatement(connection).executeUpdate(sqlQuery);
    }

    private Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
