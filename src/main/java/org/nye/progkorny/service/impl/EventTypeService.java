package org.nye.progkorny.service.impl;

import org.nye.progkorny.model.EventType;
import org.nye.progkorny.service.EventTypeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService implements EventTypeServiceInterface {
    @Override
    public List<EventType> getAllEventType() {
        return null;
    }

    @Override
    public EventType getEventType(int id) {
        return null;
    }

    @Override
    public EventType getEventTypeByName(String name) {
        return null;
    }
}
