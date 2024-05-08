package org.nye.progkorny.service.impl;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.service.EventServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class EventService implements EventServiceInterface {
    @Override
    public boolean addEvent(Event event) {
        return false;
    }

    @Override
    public List<Event> getAllEvent() {
        return null;
    }

    @Override
    public Event getEventById(int id) {
        return null;
    }

    @Override
    public Event getEventByEventType(int id) {
        return null;
    }

    @Override
    public Event getEventByLocation(String location) {
        return null;
    }

    @Override
    public Event getEventByDescription(String description) {
        return null;
    }

    @Override
    public Event getEventByUserId(int id) {
        return null;
    }

    @Override
    public Event getEventByTime(LocalTime time) {
        return null;
    }

    @Override
    public Event getEventByDate(LocalTime date) {
        return null;
    }

    @Override
    public boolean updateEvent(Event event) {
        return false;
    }

    @Override
    public boolean deleteEvent(int id) {
        return false;
    }
}
