package org.nye.progkorny.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.controller.EventController;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.impl.EventRepository;
import org.nye.progkorny.service.impl.EventService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @Test
    public void testInsertEvent() {
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
        when(eventRepository.insertEvent(event)).thenReturn(true);
        EventService eventService = new EventService(eventRepository);
        boolean result = eventService.addEvent(event);
        assertTrue(result);
        verify(eventRepository, times(1)).insertEvent(event);
    }

    @Test
    public void testGetAllEvent() throws SQLException {
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1),
                new Event(200, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1)
        );
        when(eventRepository.getAllEvent()).thenReturn(events);
        EventService eventService = new EventService(eventRepository);
        List<Event> result = eventService.getAllEvent();
        assertEquals(events.size(), result.size());
    }

    @Test
    public void testGetEventById() throws SQLException{
        int id = 1;
        Event event = new Event(id, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
        when(eventRepository.getEventById(id)).thenReturn(event);
        EventService eventService = new EventService(eventRepository);
        Event result = eventService.getEventById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventByUserId() throws SQLException {
        int userId = 1;
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", userId),
                new Event(200, Timestamp.from(Instant.now()), "TEST", 1, "TEST", userId)
        );
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", userId);
        when(eventRepository.getEventByUserId(userId)).thenReturn(events);
        List<Event> result = eventRepository.getEventByUserId(userId);
        assertEquals(result.size(),2);
    }
    @Test
    public void testGetEventByEventTypeId() throws SQLException {
        int eventTypeId = 1;
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.from(Instant.now()), "TEST", eventTypeId, "TEST", 1),
                new Event(200, Timestamp.from(Instant.now()), "TEST", eventTypeId, "TEST", 1)
        );
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", eventTypeId, "TEST", 1);
        when(eventRepository.getEventByEventTypeId(eventTypeId)).thenReturn(events);
        List<Event> result = eventRepository.getEventByEventTypeId(eventTypeId);
        assertEquals(result.size(),2);
    }

    @Test
    public void testGetEventByName() throws SQLException {
        String name = "B";
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, name, 1);
        when(eventRepository.getEventByName(name)).thenReturn(event);
        Event result = eventRepository.getEventByName(name);
        assertEquals(name, result.getName());
    }
    @Test
    public void testGetEventByLocation() throws SQLException {
        String location = "B";
        Event event = new Event(100, Timestamp.from(Instant.now()), location, 1, "B", 1);
        when(eventRepository.getEventByLocation(location)).thenReturn(event);
        Event result = eventRepository.getEventByLocation(location);
        assertEquals(location, result.getLocation());
    }

    @Test
    public void testGetEventByDateTime() throws SQLException {
        Timestamp datetime = Timestamp.from(Instant.now());
        Event event = new Event(100, datetime, "B", 1, "B", 1);
        when(eventRepository.getEventByDateTime(datetime)).thenReturn(event);
        Event result = eventRepository.getEventByDateTime(datetime);
        assertEquals(datetime, result.getDatetime());
    }

}
