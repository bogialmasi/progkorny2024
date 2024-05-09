package org.nye.progkorny.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericDataAccessInterface<TInput> {
    List<TInput> query(String query) throws SQLException;
    int upsert(String query);
}
