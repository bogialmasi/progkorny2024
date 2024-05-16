package org.nye.progkorny.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeServiceTest {

    @Mock
    private EventTypeRepository eventTypeRepository;

    // C

    @Test
    public void testInsertEventType() throws SQLException {
        EventType eventType = new EventType(100, "TESTSUBJECT");
        when(eventTypeRepository.insertEventType(eventType)).thenReturn(true);
        EventTypeService eventTypeService = new EventTypeService(eventTypeRepository);
        boolean result = eventTypeService.addEventType(eventType);
        assertTrue(result);
        verify(eventTypeRepository, times(1)).insertEventType(eventType);
    }

    // R

    @Test
    public void testGetAllEventType() throws SQLException {
        List<EventType> eventTypes = Arrays.asList(
                new EventType(100, "TESTSUBJECT"),
                new EventType(100, "TESTSUBJECT")
        );
        when(eventTypeRepository.getAllEventType()).thenReturn(eventTypes);
        EventTypeService eventTypeService = new EventTypeService(eventTypeRepository);
        List<EventType> result = eventTypeService.getAllEventType();
        assertEquals(eventTypes.size(), result.size());
    }

    @Test
    public void testGetEventTypeById() throws SQLException{
        int id = 1;
        EventType eventType = new EventType(id, "TESTSUBJECT");
        when(eventTypeRepository.getEventTypeById(id)).thenReturn(eventType);
        EventTypeService eventTypeService = new EventTypeService(eventTypeRepository);
        EventType result = eventTypeService.getEventTypeById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        String name = "B";
        EventType eventType = new EventType(1, name);
        when(eventTypeRepository.getEventTypeByName(name)).thenReturn(eventType);
        EventType result = eventTypeRepository.getEventTypeByName(name);
        assertEquals(name, result.getName());
    }

    // U
    @Test
    public void testUpdateEventType() throws SQLException{
        when(eventTypeRepository.updateEventType(any(EventType.class))).thenReturn(true);
        EventTypeService eventTypeService = new EventTypeService(eventTypeRepository);
        boolean result = eventTypeService.updateEventType(new EventType(1, "TESTSUBJECT"));
        assertEquals(result, true);
    }

    // D
    @Test
    public void testDeleteEventType() throws SQLException{
        int id = 1;
        when(eventTypeRepository.deleteEventType(id)).thenReturn(true);
        EventTypeService eventTypeService = new EventTypeService(eventTypeRepository);
        boolean result = eventTypeService.deleteEventType(id);
        assertEquals(result, true);
    }
}
