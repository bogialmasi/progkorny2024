package org.nye.progkorny.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.service.impl.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private EventService eventService;

    // C
    @Test
    public void testInsertEvent() {
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
        when(eventService.addEvent(event)).thenReturn(true);
        EventController eventController = new EventController(eventService);
        ResponseEntity<Void> result = eventController.insertEvent(event);
        verify(eventService, times(1)).addEvent(event);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    // R

    @Test
    public void testGetAllEvent() throws SQLException {
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1),
                new Event(200, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1)
        );
        when(eventService.getAllEvent()).thenReturn(events);
        EventController eventController = new EventController(eventService);
        List<Event> result = eventController.getAllEvent();
        assertEquals(events.size(), result.size());
    }

    @Test
    public void testGetEventById() throws SQLException{
        int id = 1;
        Event event = new Event(id, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
        when(eventService.getEventById(id)).thenReturn(event);
        EventController eventController = new EventController(eventService);
        Event result = eventController.getEventById(id);
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
        when(eventService.getEventByUserId(userId)).thenReturn(events);
        EventController eventController = new EventController(eventService);
        List<Event> result = eventController.getEventByUserId(userId);
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
        when(eventService.getEventByEventTypeId(eventTypeId)).thenReturn(events);
        EventController eventController = new EventController(eventService);
        List<Event> result = eventController.getEventByEventTypeId(eventTypeId);
        assertEquals(result.size(),2);
    }

    @Test
    public void testGetEventByName() throws SQLException {
        String name = "B";
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, name, 1);
        when(eventService.getEventByName(name)).thenReturn(event);
        EventController eventController = new EventController(eventService);
        Event result = eventController.getEventByName(name);
        assertEquals(name, result.getName());
    }
    @Test
    public void testGetEventByLocation() throws SQLException {
        String location = "B";
        Event event = new Event(100, Timestamp.from(Instant.now()), location, 1, "B", 1);
        when(eventService.getEventByLocation(location)).thenReturn(event);
        EventController eventController = new EventController(eventService);
        Event result = eventController.getEventByLocation(location);
        assertEquals(location, result.getLocation());
    }

    @Test
    public void testGetEventByDateTime() throws SQLException {
        Timestamp datetime = Timestamp.from(Instant.now());
        Event event = new Event(100, datetime, "B", 1, "B", 1);
        when(eventService.getEventByDateTime(datetime)).thenReturn(event);
        EventController eventController = new EventController(eventService);
        Event result = eventController.getEventByDateTime(datetime);
        assertEquals(datetime, result.getDatetime());
    }

    // U

    @Test
    public void testUpdateEvent() throws SQLException{
        when(eventService.updateEvent(any(Event.class))).thenReturn(true);
        boolean result = eventService.updateEvent(new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1));
        assertTrue(result);
    }

    // D

    @Test
    public void testDeleteEvent() throws SQLException{
        int id = 1;
        when(eventService.deleteEvent(id)).thenReturn(true);
        boolean result = eventService.deleteEvent(id);
        assertTrue(result);
    }

}
