package org.nye.progkorny.repository;

import org.nye.progkorny.model.Event;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventRepositoryInterface {
    List<Event> getAllEvent() throws SQLException;
    Event getEventById(int id) throws SQLException;
    Event getEventByName(String name) throws SQLException;
    Event getEventByLocation(String location) throws SQLException;
    Event getEventByDateTime(int datetime) throws SQLException;
    List<Event> getEventByEventTypeId(int id) throws SQLException;
    List<Event> getEventByUserId(int id) throws SQLException;

    boolean deleteEvent(int id);
    boolean updateEvent(Event event);
    boolean insertEvent(Event event);

}
