package org.nye.progkorny.service;

import org.nye.progkorny.model.Event;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface EventServiceInterface {
    // C
    boolean addEvent(Event event) throws SQLException;
    // R
    List<Event> getAllEvent() throws SQLException;
    Event getEventById(int id) throws SQLException;
    List<Event> getEventByEventTypeId(int id) throws SQLException;
    Event getEventByLocation(String location) throws SQLException;
    Event getEventByName(String name) throws SQLException;
    List<Event> getEventByUserId(int id) throws SQLException;
    Event getEventByDateTime(Timestamp datetime) throws SQLException;
    // U
    boolean updateEvent(Event event) throws SQLException;
    // D
    boolean deleteEvent(int id) throws SQLException;
}
