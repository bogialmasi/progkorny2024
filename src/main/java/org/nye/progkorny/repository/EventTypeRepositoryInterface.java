package org.nye.progkorny.repository;

import org.nye.progkorny.model.EventType;

import java.util.List;

public interface EventTypeRepositoryInterface {
    EventType getEventTypeById(int id);
    EventType getEventTypeByName(String name);

    List<EventType> getAllEventType();

    boolean insertEventType(EventType eventType);

    boolean updateEventType(EventType eventType);

    boolean deleteEventType(int id);
}
