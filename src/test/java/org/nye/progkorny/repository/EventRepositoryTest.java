package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.EventRepository;
import org.nye.progkorny.repository.impl.UserRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testGetAllEvent() throws SQLException {
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(event);
        when(eventRepository.getAllEvent()).thenReturn(expectedEvents);
        List<Event> actualEvent = eventRepository.getAllEvent();
        assertEquals(expectedEvents, actualEvent);
        verify(eventRepository, times(1)).getAllEvent();
    }
}
