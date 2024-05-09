package org.nye.progkorny.repository;

import org.nye.progkorny.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventRepositoryInterface {
    List<Event> getAllEvent();
    Event getEventById(int id);
    Event getEventByName(String name);
    Event getEventByLocation(String location);
    Event getEventByDateTime(int datetime);
    Event getEventByUserId(int id);

    Event deleteEvent(int id);
    Event updateEvent(Event event);
    Event insertEvent(Event event);

}
