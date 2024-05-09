package org.nye.progkorny.service;

import org.nye.progkorny.model.EventType;

import java.util.List;

public interface EventTypeServiceInterface {
// C
    // R
    List<EventType> getAllEventType();
    EventType getEventTypeByName(String name);
    EventType getEventTypeById(int id);
}
