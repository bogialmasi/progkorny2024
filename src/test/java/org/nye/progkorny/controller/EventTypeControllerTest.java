package org.nye.progkorny.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.service.impl.EventTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeControllerTest {

    @Mock
    private EventTypeService eventTypeService;

    // C

    @Test
    public void testInsertEventType() throws SQLException {
        EventType eventType = new EventType(100, "TESTSUBJECT");
        when(eventTypeService.addEventType(eventType)).thenReturn(true);
        EventTypeController eventTypeController = new EventTypeController(eventTypeService);
        ResponseEntity<Void> result = eventTypeController.insertEventType(eventType);
        verify(eventTypeService, times(1)).addEventType(eventType);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    // R

    @Test
    public void testGetAllEventType() throws SQLException {
        List<EventType> eventTypes = Arrays.asList(
                new EventType(100, "TESTSUBJECT"),
                new EventType(200, "TESTSUBJECT")
        );
        when(eventTypeService.getAllEventType()).thenReturn(eventTypes);
        EventTypeController eventTypeController = new EventTypeController(eventTypeService);
        List<EventType> result = eventTypeController.getAllEventType();
        assertEquals(eventTypes.size(), result.size());
    }

    @Test
    public void testGetEventTypeById() throws SQLException{
        int id = 1;
        EventType eventType = new EventType(id, "TESTSUBJECT");
        when(eventTypeService.getEventTypeById(id)).thenReturn(eventType);
        EventTypeController eventTypeController = new EventTypeController(eventTypeService);
        EventType result = eventTypeController.getEventTypeById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        String name = "B";
        EventType eventType = new EventType(1, name);
        when(eventTypeService.getEventTypeByName(name)).thenReturn(eventType);
        EventTypeController eventTypeController = new EventTypeController(eventTypeService);
        EventType result = eventTypeController.getEventTypeByName(name);
        assertEquals(name, result.getName());
    }

    // U

    @Test
    public void testUpdateEventType() throws SQLException{
        when(eventTypeService.updateEventType(any(EventType.class))).thenReturn(true);
        boolean result = eventTypeService.updateEventType(new EventType(100, "TESTSUBJECT"));
        assertTrue(result);
    }

    // D

    @Test
    public void testDeleteEventType() throws SQLException{
        int id = 1;
        when(eventTypeService.deleteEventType(id)).thenReturn(true);
        boolean result = eventTypeService.deleteEventType(id);
        assertTrue(result);
    }
}
