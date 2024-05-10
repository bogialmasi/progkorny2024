package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.EventRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository extends GenericDataAccess<Event> implements EventRepositoryInterface {

    // C

    @Override
    public boolean insertEvent(Event event) {
        int rowsAffected = upsert("INSERT INTO event " +
                "(datetime, location, eventTypeId, name, userId) VALUES" +
                event.getDatetime() + event.getLocation() + event.getEventTypeId() +
                event.getName() + event.getUserId() + ";");
        return rowsAffected == 1;
    }

    // R

    @Override
    public List<Event> getAllEvent() throws SQLException {
        String sqlQuery = "SELECT * FROM event;";
        return query(sqlQuery);
    }

    @Override
    public Event getEventById(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event WHERE id = %d;", id);
        return query(sqlQuery).get(0);
    }

    @Override
    public Event getEventByName(String name) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event where name = '%s';", name);
        return query(sqlQuery).get(0);
    }

    @Override
    public Event getEventByLocation(String location) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event where location = '%s';", location);
        return query(sqlQuery).get(0);
    }

    @Override
    public Event getEventByDateTime(int datetime) throws SQLException {
        String sqlQuery = "SELECT * FROM event where name =" + datetime + ";";
        return query(sqlQuery).get(0);
    }

    @Override
    public List<Event> getEventByEventTypeId(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event where eventTypeId =" + id + ";");
        return query(sqlQuery);
    }

    @Override
    public List<Event> getEventByUserId(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event where userId =" + id + ";");
        return query(sqlQuery);
    }

    // U

    @Override
    public boolean updateEvent(Event event) {
        int rowsAffected = upsert("UPDATE event " +
                "SET datetime = " + event.getDatetime() +
                ", location = " + event.getLocation() +
                ", eventTypeId = " + event.getEventTypeId() +
                ", name = " + event.getName() +
                ", userId = " + event.getUserId() +
                "WHERE id = %d;");
        return rowsAffected == 1;
    }


    // D

    @Override
    public boolean deleteEvent(int id) {
        String sqlQuery = String.format("DELETE FROM event WHERE id = %d", id);
        return (delete(sqlQuery));
    }

    @Override
    List<Event> map(ResultSet resultSet) throws SQLException {
        List<Event> events = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            Timestamp datetime = resultSet.getTimestamp(3);
            String location = resultSet.getString(2);
            int eventTypeID = resultSet.getInt(3);
            String name = resultSet.getString(2);
            int userId = resultSet.getInt(3);
            events.add(new Event(id, datetime, location, eventTypeID, name, userId));
        }

        return events;
    }
}
