package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.EventTypeRepository;
import org.nye.progkorny.repository.impl.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeRepositoryTest {
    @Mock
    EventTypeRepository eventTypeRepository;
    private EventType eventType;
    @BeforeEach
    public void setUp(){
        eventType = new EventType(100, "B");
    }

    @Test
    public void testGetAllEventType() throws SQLException {
        List<EventType> expectedEventTypes = new ArrayList<>();
        expectedEventTypes.add(eventType);
        when(eventTypeRepository.getAllEventType()).thenReturn(expectedEventTypes);
        List<EventType> actualEventTypes = eventTypeRepository.getAllEventType();
        assertEquals(expectedEventTypes, actualEventTypes);
        verify(eventTypeRepository, times(1)).getAllEventType();
    }
}
