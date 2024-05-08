package org.nye.progkorny.repository;

import org.nye.progkorny.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventRepositoryInterface {
    List<Event> getEvent();
    Event getEventById(int id);
    Event getEventByDescripton(String description);
    Event getEventByLocation(String location);
    Event getEventByDate(LocalDate date);
    Event getEventByTime(LocalTime time);
    Event getEventByUserId(int id);

    Event deleteEvent(int id);
    Event updateEvent(Event event);
    Event insertEvent(Event event);

}
