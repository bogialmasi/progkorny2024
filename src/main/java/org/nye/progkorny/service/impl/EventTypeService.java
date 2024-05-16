package org.nye.progkorny.service.impl;

import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.EventRepositoryInterface;
import org.nye.progkorny.repository.EventTypeRepositoryInterface;
import org.nye.progkorny.service.EventTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EventTypeService implements EventTypeServiceInterface {

    @Autowired
    EventTypeRepositoryInterface eventTypeRepository;

    public EventTypeService(EventTypeRepositoryInterface eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public List<EventType> getAllEventType() throws SQLException {
        return eventTypeRepository.getAllEventType();
    }

    @Override
    public EventType getEventTypeByName(String name) throws SQLException {
        return eventTypeRepository.getEventTypeByName(name);
    }

    public boolean addEventType(EventType eventType) throws SQLException {
        return eventTypeRepository.insertEventType(eventType);
    }

    public EventType getEventTypeById(int id) throws SQLException {
        return eventTypeRepository.getEventTypeById(id);
    }

    public boolean updateEventType(EventType eventType) throws SQLException {
        return eventTypeRepository.updateEventType(eventType);
    }

    public boolean deleteEventType(int id) throws SQLException {
        return eventTypeRepository.deleteEventType(id);
    }
}
