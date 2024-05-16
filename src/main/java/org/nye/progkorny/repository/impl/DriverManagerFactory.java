package org.nye.progkorny.repository.impl;

import org.nye.progkorny.repository.DriverManagerFactoryInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DriverManagerFactory implements DriverManagerFactoryInterface {

    @Value("${spring.datasource.url}")
    public String dbUrl;
    @Value("${spring.datasource.username}")
    public String user;
    @Value("${spring.datasource.password}")
    public String pw;

    Connection connection;

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            return connection = DriverManager.getConnection(dbUrl, user, pw);
        }

        return connection;
    }
}
