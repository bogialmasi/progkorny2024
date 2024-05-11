package org.nye.progkorny.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.impl.EventRepository;
import org.nye.progkorny.service.impl.EventService;
import org.nye.progkorny.service.impl.EventTypeService;

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

    @InjectMocks
    private EventController eventController;
    @Mock
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;

    @Test
    public void testAddEvent() {
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
        when(eventRepository.insertEvent(event)).thenReturn(true);
        EventService eventService = new EventService(eventRepository);
        boolean result = eventService.addEvent(event);
        assertTrue(result);
        verify(eventRepository, times(1)).insertEvent(event);
    }

    @Test
    public void testAllGetEvent() throws SQLException {
        List<Event> events = Arrays.asList(
                new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1),
                new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1)
                );
        when(eventRepository.getAllEvent()).thenReturn(events);
        EventService eventService = new EventService(eventRepository);
        List<Event> result = eventService.getAllEvent();
        assertEquals(events.size(), result.size());
    }

    @Test
    public void testEventUserById() throws SQLException{
        int id = 1;
        Event event = new Event(100, Timestamp.from(Instant.now()), "TEST", 1, "TEST", 1);
        when(eventRepository.getEventById(id)).thenReturn(event);
        EventService eventService = new EventService(eventRepository);
        Event result = eventService.getEventById(id);
        assertEquals(id, event.getId());
    }
}
