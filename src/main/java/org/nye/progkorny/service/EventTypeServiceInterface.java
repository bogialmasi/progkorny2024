package org.nye.progkorny.service;

import org.nye.progkorny.model.EventType;

import java.util.List;

public interface EventTypeServiceInterface {
// C
    // R
    List<EventType> getAllEventType();
    EventType getEventType(int id);//???????
    EventType getEventTypeByName(String name);
}
