package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.impl.EventTypeRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeRepositoryTest {
    @Mock
    EventTypeRepository eventTypeRepository;
    private EventType eventType;

    @BeforeEach
    public void setUp() {
        eventType = new EventType(100, "B");
    }

    // C
    @Test
    public void testInsertEventType_Successful() throws SQLException {
        when(eventTypeRepository.insertEventType(eventType)).thenReturn(true);
        assertTrue(eventTypeRepository.insertEventType(eventType));
        verify(eventTypeRepository, times(1)).insertEventType(eventType);
    }

    @Test
    public void testInsertEventType_Failure() throws SQLException {
        when(eventTypeRepository.insertEventType(eventType)).thenReturn(false);
        boolean result = eventTypeRepository.insertEventType(eventType);
        assertFalse(result);
        verify(eventTypeRepository, times(1)).insertEventType(eventType);
    }


    // R
    @Test
    public void testGetAllEventType() throws SQLException {
        List<EventType> expectedEventTypes = new ArrayList<>();
        expectedEventTypes.add(eventType);
        when(eventTypeRepository.getAllEventType()).thenReturn(expectedEventTypes);
        List<EventType> actualEventTypes = eventTypeRepository.getAllEventType();
        assertEquals(expectedEventTypes, actualEventTypes);
        verify(eventTypeRepository, times(1)).getAllEventType();
    }

    @Test
    public void testGetEventTypeById() throws SQLException {
        int id = eventType.getId();
        when(eventTypeRepository.getEventTypeById(id)).thenReturn(eventType);
        EventType actualEventType = eventTypeRepository.getEventTypeById(id);
        assertEquals(eventType, actualEventType);
        verify(eventTypeRepository, times(1)).getEventTypeById(id);
    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        String name = eventType.getName();
        when(eventTypeRepository.getEventTypeByName(name)).thenReturn(eventType);
        EventType actualEventType = eventTypeRepository.getEventTypeByName(name);
        assertEquals(eventType, actualEventType);
        verify(eventTypeRepository, times(1)).getEventTypeByName(name);
    }

    // U

    @Test
    public void testUpdateEventType_Successful() throws SQLException {
        EventTypeRepository eventTypeRepository = mock(EventTypeRepository.class);
        EventType eventType = new EventType(200, "BC");
        when(eventTypeRepository.updateEventType(eventType)).thenReturn(true);
        assertTrue(eventTypeRepository.updateEventType(eventType));
        verify(eventTypeRepository, times(1)).updateEventType(eventType);
    }

    @Test
    public void testUpdateEventType_Failure() throws SQLException {
        EventTypeRepository eventTypeRepository = mock(EventTypeRepository.class);
        EventType eventType = new EventType(100, "B");
        when(eventTypeRepository.updateEventType(eventType)).thenReturn(false);
        assertFalse(eventTypeRepository.updateEventType(eventType));
        verify(eventTypeRepository, times(1)).updateEventType(eventType);
    }

    // D

    @Test
    public void testDeleteEventType_Successful(){
        EventTypeRepository eventTypeRepository = mock(EventTypeRepository.class);
        int id = 1;
        when(eventTypeRepository.deleteEventType(id)).thenReturn(true);
        assertTrue(eventTypeRepository.deleteEventType(id));
        verify(eventTypeRepository, times(1)).deleteEventType(id);
    }

    @Test
    public void testDeleteEventType_Failure(){
        EventTypeRepository eventTypeRepository = mock(EventTypeRepository.class);
        int id = 1;
        when(eventTypeRepository.deleteEventType(id)).thenReturn(false);
        assertFalse(eventTypeRepository.deleteEventType(id));
        verify(eventTypeRepository, times(1)).deleteEventType(id);
    }
}
