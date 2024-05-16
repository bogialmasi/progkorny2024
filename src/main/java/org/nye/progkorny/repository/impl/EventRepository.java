package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.EventRepositoryInterface;
import org.nye.progkorny.repository.GenericDataAccessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository implements EventRepositoryInterface {


    @Autowired
    private GenericDataAccessInterface dac;

    public EventRepository(GenericDataAccessInterface dac) {
        this.dac = dac;
    }

    // C
    @Override
    public boolean insertEvent(Event event) throws SQLException {
        int rowsAffected = dac.upsert("INSERT INTO event (datetime, location, eventtypeid, name, userid) VALUES('" +
                event.getDatetime() +"', '" + event.getLocation() +"', '" + event.getEventTypeId() + "', '" +
                event.getName() +"', '" + event.getUserId() + "');");
        return rowsAffected == 1;
    }

    // R

    @Override
    public List<Event> getAllEvent() throws SQLException {
        String sqlQuery = "SELECT * FROM event;";
        return map(dac.query(sqlQuery));
    }

    @Override
    public Event getEventById(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event WHERE id = %d;", id);
        return map(dac.query(sqlQuery)).get(0);
    }

    @Override
    public Event getEventByName(String name) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event WHERE name = '%s';", name);
        return map(dac.query(sqlQuery)).get(0);
    }

    @Override
    public Event getEventByLocation(String location) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event WHERE location = '%s';", location);
        return map(dac.query(sqlQuery)).get(0);
    }

    @Override
    public Event getEventByDateTime(Timestamp datetime) throws SQLException {
        String sqlQuery = "SELECT * FROM event WHERE datetime = '" + datetime + "';";
        return map(dac.query(sqlQuery)).get(0);
    }

    @Override
    public List<Event> getEventByEventTypeId(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event WHERE eventtypeid =" + id + ";");
        return map(dac.query(sqlQuery));
    }

    @Override
    public List<Event> getEventByUserId(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM event WHERE userid =" + id + ";");
        return map(dac.query(sqlQuery));
    }

    // U

    @Override
    public boolean updateEvent(Event event) throws SQLException {
        int rowsAffected = dac.upsert("UPDATE event " +
                "SET datetime = '" + event.getDatetime() +
                "', location = '" + event.getLocation() +
                "', eventtypeid = " + event.getEventTypeId() +
                ", name = '" + event.getName() +
                "', userid = " + event.getUserId() +
                " WHERE id = " + event.getId() + ";");
        return rowsAffected == 1;
    }


    // D

    @Override
    public boolean deleteEvent(int id) throws SQLException {
        String sqlQuery = String.format("DELETE FROM event WHERE id = %d", id);
        return (dac.delete(sqlQuery));
    }

    List<Event> map(ResultSet resultSet) throws SQLException {
        List<Event> events = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            Timestamp datetime = resultSet.getTimestamp(2);
            String location = resultSet.getString(3);
            int eventTypeID = resultSet.getInt(4);
            String name = resultSet.getString(5);
            int userId = resultSet.getInt(6);
            events.add(new Event(id, datetime, location, eventTypeID, name, userId));
        }

        return events;
    }
}
