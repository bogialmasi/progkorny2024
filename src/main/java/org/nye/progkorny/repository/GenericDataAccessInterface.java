package org.nye.progkorny.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDataAccessInterface {
    ResultSet query(String query) throws SQLException;
    int upsert(String query) throws SQLException;
    boolean delete(String sqlQuery) throws SQLException;
}
