package org.nye.progkorny.repository.impl;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.EventRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class EventRepository implements EventRepositoryInterface {
    @Override
    public List<Event> getEvent() {
        return null;
    }

    @Override
    public Event getEventById(int id) {
        return null;
    }

    @Override
    public Event getEventByDescripton(String description) {
        return null;
    }

    @Override
    public Event getEventByLocation(String location) {
        return null;
    }

    @Override
    public Event getEventByDate(LocalDate date) {
        return null;
    }

    @Override
    public Event getEventByTime(LocalTime time) {
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
