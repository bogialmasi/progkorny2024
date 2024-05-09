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
    Event getEventByEventTypeId(int id);
    Event getEventByLocation(String location);
    Event getEventByName(String name);
    Event getEventByUserId(int id);
    Event getEventByDateTime(int datetime);
    // U
    boolean updateEvent(Event event);
    // D
    boolean deleteEvent(int id);
}
