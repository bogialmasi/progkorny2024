package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.EventTypeRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventTypeRepository implements EventTypeRepositoryInterface {
    @Override
    public List<EventType> getEventType() {
        return null;
    }

    @Override
    public EventType getEventTypeById(int id) {
        return null;
    }

    @Override
    public EventType getEventTypeByName(String name) {
        return null;
    }
}
