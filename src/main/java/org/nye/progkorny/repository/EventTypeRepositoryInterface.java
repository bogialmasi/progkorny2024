package org.nye.progkorny.repository;

import org.nye.progkorny.model.EventType;

import java.sql.SQLException;
import java.util.List;

public interface EventTypeRepositoryInterface {
    EventType getEventTypeById(int id) throws SQLException;

    EventType getEventTypeByName(String name) throws SQLException;

    List<EventType> getAllEventType() throws SQLException;

    boolean insertEventType(EventType eventType) throws SQLException;

    boolean updateEventType(EventType eventType) throws SQLException;

    boolean deleteEventType(int id) throws SQLException;
}
