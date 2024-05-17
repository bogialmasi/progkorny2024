package org.nye.progkorny.service;

import org.nye.progkorny.model.EventType;

import java.sql.SQLException;
import java.util.List;

public interface EventTypeServiceInterface {
    boolean addEventType(EventType eventType) throws SQLException;

    List<EventType> getAllEventType() throws SQLException;

    EventType getEventTypeByName(String name) throws SQLException;

    EventType getEventTypeById(int id) throws SQLException;

    boolean updateEventType(EventType eventType) throws SQLException;

    boolean deleteEventType(int id) throws SQLException;
}
