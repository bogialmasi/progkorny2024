package org.nye.progkorny.repository.impl;


import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.EventTypeRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventTypeRepository extends GenericDataAccess<EventType> implements EventTypeRepositoryInterface {

    // C

    @Override
    public boolean insertEventType(EventType eventType) {
        int rowsAffected = upsert("INSERT INTO eventtype " +
                "(name) VALUES('" + eventType.getName()+ "');");
        return rowsAffected == 1;
    }

    // R

    @Override
    public EventType getEventTypeById(int id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM eventtype WHERE id = %d;", id);
        return query(sqlQuery).get(0);
    }

    @Override
    public EventType getEventTypeByName(String name) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM eventtype WHERE name = '%s';", name);
        return query(sqlQuery).get(0);
    }

    @Override
    public List<EventType> getAllEventType() throws SQLException {
        String sqlQuery = "SELECT * FROM eventtype;";
        return query(sqlQuery);
    }


    // U

    @Override
    public boolean updateEventType(EventType eventType) {
        int rowsAffected = upsert("UPDATE eventtype SET name = '" + eventType.getName() + "' WHERE id = " + eventType.getId() + ";");
        return rowsAffected == 1;
    }

    // D

    @Override
    public boolean deleteEventType(int id) {
        String sqlQuery = String.format("DELETE FROM eventtype WHERE id = %d", id);
        return (delete(sqlQuery));
    }

    @Override
    List<EventType> map(ResultSet resultSet) throws SQLException {
        List<EventType> eventTypes = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            eventTypes.add(new EventType(id, name));
        }
        return eventTypes;
    }
}
