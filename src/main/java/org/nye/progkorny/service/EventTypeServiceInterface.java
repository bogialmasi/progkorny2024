package org.nye.progkorny.service;

import org.nye.progkorny.model.EventType;

import java.sql.SQLException;
import java.util.List;

public interface EventTypeServiceInterface {
// C
    // R
    List<EventType> getAllEventType() throws SQLException;
    EventType getEventTypeByName(String name) throws SQLException;
    EventType getEventTypeById(int id) throws SQLException;
}
