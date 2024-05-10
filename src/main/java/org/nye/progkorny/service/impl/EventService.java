package org.nye.progkorny.service.impl;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.EventRepositoryInterface;
import org.nye.progkorny.service.EventServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventService implements EventServiceInterface {

    @Autowired
    private EventRepositoryInterface eventRepository;

    public EventService(EventRepositoryInterface eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public boolean addEvent(Event event) {
        return eventRepository.insertEvent(event);
    }

    @Override
    public List<Event> getAllEvent() throws SQLException {
        return eventRepository.getAllEvent();
    }

    @Override
    public Event getEventById(int id) throws SQLException {

        return eventRepository.getEventById(id);
    }

    @Override
    public List<Event> getEventByEventTypeId(int id) {

        return getEventByEventTypeId(id);
    }

    @Override
    public Event getEventByLocation(String location) throws SQLException {

        return eventRepository.getEventByLocation(location);
    }

    @Override
    public Event getEventByName(String name) throws SQLException {

        return eventRepository.getEventByName(name);
    }

    @Override
    public List<Event> getEventByUserId(int id) throws SQLException {

        return eventRepository.getEventByUserId(id);
    }

    @Override
    public Event getEventByDateTime(int datetime) throws SQLException {
        return eventRepository.getEventByDateTime(datetime);
    }

    @Override
    public boolean updateEvent(Event event) {
        return eventRepository.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(int id) {
        return eventRepository.deleteEvent(id);
    }
}
