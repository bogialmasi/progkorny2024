package org.nye.progkorny.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.impl.EventTypeRepository;
import org.nye.progkorny.service.impl.EventTypeService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeServiceTest {
    private EventTypeService eventTypeService;

    @Mock
    private EventTypeRepository eventTypeRepository;

    @BeforeEach
    public void setUp(){
        eventTypeService = new EventTypeService(eventTypeRepository);
    }

    @Test
    public void testInsertEventType_Success() throws SQLException {
        EventType eventType = new EventType(100, "TESTSUBJECT");
        when(eventTypeRepository.insertEventType(eventType)).thenReturn(true);
        boolean result = eventTypeService.addEventType(eventType);
        verify(eventTypeRepository, times(1)).insertEventType(eventType);
        assertTrue(result);
    }
    @Test
    public void testInsertEventType_Failure() throws SQLException {
        EventType eventType = new EventType(100, "TESTSUBJECT");
        when(eventTypeRepository.insertEventType(eventType)).thenReturn(false);
        boolean result = eventTypeService.addEventType(eventType);
        verify(eventTypeRepository, times(1)).insertEventType(eventType);
        assertFalse(result);
    }
    @Test
    public void testGetAllEventType() throws SQLException {
        List<EventType> eventTypes = Arrays.asList(
                new EventType(100, "TESTSUBJECT"),
                new EventType(100, "TESTSUBJECT")
        );
        when(eventTypeRepository.getAllEventType()).thenReturn(eventTypes);
        List<EventType> result = eventTypeService.getAllEventType();
        assertEquals(eventTypes.size(), result.size());
    }

    @Test
    public void testGetEventTypeById() throws SQLException{
        int id = 1;
        EventType eventType = new EventType(id, "TESTSUBJECT");
        when(eventTypeRepository.getEventTypeById(id)).thenReturn(eventType);
        EventType result = eventTypeService.getEventTypeById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        String name = "B";
        EventType eventType = new EventType(1, name);
        when(eventTypeRepository.getEventTypeByName(name)).thenReturn(eventType);
        EventType result = eventTypeService.getEventTypeByName(name);
        assertEquals(name, result.getName());
    }
    @Test
    public void testUpdateEventType_Success() throws SQLException{
        when(eventTypeRepository.updateEventType(any(EventType.class))).thenReturn(true);
        boolean result = eventTypeService.updateEventType(new EventType(1, "TESTSUBJECT"));
        assertTrue(result);
    }
    @Test
    public void testUpdateEventType_Failure() throws SQLException{
        when(eventTypeRepository.updateEventType(any(EventType.class))).thenReturn(false);
        boolean result = eventTypeService.updateEventType(new EventType(1, "TESTSUBJECT"));
        assertFalse(result);
    }
    @Test
    public void testDeleteEventType_Success() throws SQLException{
        int id = 1;
        when(eventTypeRepository.deleteEventType(id)).thenReturn(true);
        boolean result = eventTypeService.deleteEventType(id);
        assertTrue(result);
    }
    @Test
    public void testDeleteEventType_Failure() throws SQLException{
        int id = 1;
        when(eventTypeRepository.deleteEventType(id)).thenReturn(false);
        boolean result = eventTypeService.deleteEventType(id);
        assertFalse(result);
    }
}
