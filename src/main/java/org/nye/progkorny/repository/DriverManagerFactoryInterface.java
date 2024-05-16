package org.nye.progkorny.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface DriverManagerFactoryInterface {
    Connection getConnection() throws SQLException;
}
