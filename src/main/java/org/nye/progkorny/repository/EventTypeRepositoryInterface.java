package org.nye.progkorny.repository;

import org.nye.progkorny.model.EventType;

import java.util.List;

public interface EventTypeRepositoryInterface {
    List<EventType> getEventType();
    EventType getEventTypeById(int id);
    EventType getEventTypeByName(String name);
}
