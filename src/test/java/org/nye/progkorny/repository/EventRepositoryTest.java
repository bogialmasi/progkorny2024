package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.EventRepository;
import org.nye.progkorny.repository.impl.UserRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryTest {
    @Mock
    EventRepository eventRepository;
    private Event event;
    @BeforeEach
    public void setUp(){
        event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
    }

    // C

    @Test
    public void testInsertEvent_Successful() throws SQLException {
        when(eventRepository.insertEvent(event)).thenReturn(true);
        assertTrue(eventRepository.insertEvent(event));
        verify(eventRepository, times(1)).insertEvent(event);
    }

    @Test
    public void testInsertEvent_Failure() throws SQLException {
        when(eventRepository.insertEvent(event)).thenReturn(false);
        boolean result = eventRepository.insertEvent(event);
        assertFalse(result);
        verify(eventRepository, times(1)).insertEvent(event);
    }

    // R
    @Test
    public void testGetAllEvent() throws SQLException {
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(event);
        when(eventRepository.getAllEvent()).thenReturn(expectedEvents);
        List<Event> actualEvent = eventRepository.getAllEvent();
        assertEquals(expectedEvents, actualEvent);
        verify(eventRepository, times(1)).getAllEvent();
    }

    @Test
    public void testGetEventById() throws SQLException {
        int id = event.getId();
        when(eventRepository.getEventById(id)).thenReturn(event);
        Event actualEvent = eventRepository.getEventById(id);
        assertEquals(event, actualEvent);
        verify(eventRepository, times(1)).getEventById(id);
    }

    @Test
    public void testGetEventByName() throws SQLException {
        String name = event.getName();
        when(eventRepository.getEventByName(name)).thenReturn(event);
        Event actualEvent = eventRepository.getEventByName(name);
        assertEquals(event, actualEvent);
        verify(eventRepository, times(1)).getEventByName(name);
    }

    @Test
    public void testGetEventByLocation() throws SQLException {
        String location = event.getLocation();
        when(eventRepository.getEventByLocation(location)).thenReturn(event);
        Event actualEvent = eventRepository.getEventByLocation(location);
        assertEquals(event, actualEvent);
        verify(eventRepository, times(1)).getEventByLocation(location);
    }

    @Test
    public void testGetEventByUserId() throws SQLException {
        int userid = event.getUserId();
        when(eventRepository.getEventByUserId(userid)).thenReturn(new ArrayList<Event>());
        List<Event> eventList = eventRepository.getEventByUserId(userid);
        assertEquals(eventList.size(), eventRepository.getEventByUserId(userid).size());
        verify(eventRepository, times(2)).getEventByUserId(userid);
    }
}
