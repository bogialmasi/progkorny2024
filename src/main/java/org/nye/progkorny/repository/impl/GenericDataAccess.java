package org.nye.progkorny.repository.impl;

import org.nye.progkorny.repository.DriverManagerFactoryInterface;
import org.nye.progkorny.repository.GenericDataAccessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class GenericDataAccess implements GenericDataAccessInterface {
    @Autowired
    private DriverManagerFactoryInterface driverManagerFactory;

    public GenericDataAccess(DriverManagerFactoryInterface driverManagerFactory) {
        this.driverManagerFactory = driverManagerFactory;
    }

    @Override
    public ResultSet query(String sqlQuery) throws SQLException {
        ResultSet resultSet = null;
        Connection connection = driverManagerFactory.getConnection();
        resultSet = queryImpl(connection, sqlQuery);
        return resultSet;
    }

    private ResultSet queryImpl(Connection connection, String sqlQuery) throws SQLException {
        return createStatement(connection).executeQuery(sqlQuery);
    }

    public boolean delete(String sqlQuery) throws SQLException {
        Connection connection = driverManagerFactory.getConnection();
        if (!deleteImpl(connection, sqlQuery)) {
            return true;
        }

        return false;
    }

    private boolean deleteImpl(Connection connection, String sqlQuery) throws SQLException {
        return createStatement(connection).execute(sqlQuery);
    }

    public int upsert(String sqlQuery) throws SQLException {
        Connection connection = driverManagerFactory.getConnection();
        return upsertImpl(connection, sqlQuery);
    }

    private int upsertImpl(Connection connection, String sqlQuery) throws SQLException {
        return createStatement(connection).executeUpdate(sqlQuery);
    }

    private Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
