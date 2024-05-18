package org.nye.progkorny.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.impl.EventRepository;
import org.nye.progkorny.service.impl.EventService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    public void setUp(){
        eventService = new EventService(eventRepository);
    }
    @Test
    public void testInsertEvent_Success() throws SQLException {
        Event event = new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1);
        when(eventRepository.insertEvent(event)).thenReturn(true);
        boolean result = eventService.addEvent(event);
        verify(eventRepository, times(1)).insertEvent(event);
        assertTrue(result);
    }
    @Test
    public void testInsertEvent_Failure() throws SQLException {
        Event event = new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1);
        when(eventRepository.insertEvent(event)).thenReturn(false);
        boolean result = eventService.addEvent(event);
        verify(eventRepository, times(1)).insertEvent(event);
        assertFalse(result);
    }
    @Test
    public void testGetAllEvent() throws SQLException {
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1),
                new Event(200, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1)
        );
        when(eventRepository.getAllEvent()).thenReturn(events);
        List<Event> result = eventService.getAllEvent();
        assertEquals(events.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void testGetEventById() throws SQLException{
        int id = 1;
        Event event = new Event(id, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1);
        when(eventRepository.getEventById(id)).thenReturn(event);
        Event result = eventService.getEventById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventByUserId() throws SQLException {
        int userId = 1;
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", userId),
                new Event(200, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", userId)
        );
        when(eventRepository.getEventByUserId(userId)).thenReturn(events);
        List<Event> result = eventService.getEventByUserId(userId);
        assertEquals(result.size(),2);
    }
    @Test
    public void testGetEventByEventTypeId() throws SQLException {
        int eventTypeId = 1;
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", eventTypeId, "TEST", 1),
                new Event(200, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", eventTypeId, "TEST", 1)
        );
        when(eventRepository.getEventByEventTypeId(eventTypeId)).thenReturn(events);
        List<Event> result = eventService.getEventByEventTypeId(eventTypeId);
        assertEquals(result.size(),2);
    }

    @Test
    public void testGetEventByName() throws SQLException {
        String name = "B";
        Event event = new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, name, 1);
        when(eventRepository.getEventByName(name)).thenReturn(event);
        Event result = eventService.getEventByName(name);
        assertEquals(name, result.getName());
    }
    @Test
    public void testGetEventByLocation() throws SQLException {
        String location = "B";
        Event event = new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), location, 1, "B", 1);
        when(eventRepository.getEventByLocation(location)).thenReturn(event);
        Event result = eventService.getEventByLocation(location);
        assertEquals(location, result.getLocation());
    }

    @Test
    public void testGetEventByDateTime() throws SQLException {
        Timestamp datetime = Timestamp.valueOf("2024-05-17 14:23:48");
        Event event = new Event(100, datetime, "B", 1, "B", 1);
        when(eventRepository.getEventByDateTime(datetime)).thenReturn(event);
        Event result = eventService.getEventByDateTime(datetime);
        assertEquals(datetime, result.getDatetime());
    }
    @Test
    public void testUpdateEvent_Success() throws SQLException{
        when(eventRepository.updateEvent(any(Event.class))).thenReturn(true);
        boolean result = eventService.updateEvent(new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1));
        assertTrue(result);
    }
    @Test
    public void testUpdateEvent_Failure() throws SQLException{
        when(eventRepository.updateEvent(any(Event.class))).thenReturn(false);
        boolean result = eventService.updateEvent(new Event(100, Timestamp.valueOf("2024-05-17 14:23:48"), "TEST", 1, "TEST", 1));
        assertFalse(result);
    }
    @Test
    public void testDeleteEvent_Success() throws SQLException{
        int id = 1;
        when(eventRepository.deleteEvent(id)).thenReturn(true);
        boolean result = eventService.deleteEvent(id);
        assertTrue(result);
    }
    @Test
    public void testDeleteEvent_Failure() throws SQLException{
        int id = 1;
        when(eventRepository.deleteEvent(id)).thenReturn(false);
        boolean result = eventService.deleteEvent(id);
        assertFalse(result);
    }
}
