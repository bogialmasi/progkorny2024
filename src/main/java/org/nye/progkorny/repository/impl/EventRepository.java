package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.EventRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository implements EventRepositoryInterface {
    @Override
    public List<Event> getAllEvent() {
        return null;
    }

    @Override
    public Event getEventById(int id) {
        return null;
    }

    @Override
    public Event getEventByName(String name) {
        return null;
    }

    @Override
    public Event getEventByLocation(String location) {
        return null;
    }

    @Override
    public Event getEventByDateTime(int datetime) {
        return null;
    }

    @Override
    public Event getEventByUserId(int id) {
        return null;
    }

    @Override
    public Event deleteEvent(int id) {
        return null;
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public Event insertEvent(Event event) {
        return null;
    }
}
