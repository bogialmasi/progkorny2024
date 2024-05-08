package org.nye.progkorny.service;

import org.nye.progkorny.model.Event;

import java.time.LocalTime;
import java.util.List;

public interface EventServiceInterface {
    // C
    boolean addEvent(Event event);
    // R
    List<Event> getAllEvent();
    Event getEventById(int id);
    Event getEventByEventType(int id);
    Event getEventByLocation(String location);
    Event getEventByDescription(String description);
    Event getEventByUserId(int id);
    Event getEventByTime(LocalTime time);
    Event getEventByDate(LocalTime date);
    // U
    boolean updateEvent(Event event);
    // D
    boolean deleteEvent(int id);
}
