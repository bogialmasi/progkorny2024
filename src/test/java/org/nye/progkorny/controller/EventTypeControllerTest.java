package org.nye.progkorny.controller;

import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeControllerTest {

    private EventTypeController eventTypeController;
    @Mock
    private EventTypeService eventTypeService;

    @BeforeEach
    public void setUp(){
        eventTypeController = new EventTypeController(eventTypeService);
    }
    @Test
    public void testInsertEventType_Success() throws SQLException {
        EventType eventType = new EventType(100, "TESTSUBJECT");
        when(eventTypeService.addEventType(eventType)).thenReturn(true);
        ResponseEntity<Void> result = eventTypeController.insertEventType(eventType);
        verify(eventTypeService, times(1)).addEventType(eventType);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
    @Test
    public void testInsertEventType_Failure() throws SQLException {
        EventType eventType = new EventType(100, "TESTSUBJECT");
        when(eventTypeService.addEventType(eventType)).thenReturn(false);
        ResponseEntity<Void> result = eventTypeController.insertEventType(eventType);
        verify(eventTypeService, times(1)).addEventType(eventType);
        assertEquals(HttpStatus.NOT_MODIFIED, result.getStatusCode());
    }
    @Test
    public void testGetAllEventType() throws SQLException {
        List<EventType> eventTypes = Arrays.asList(
                new EventType(100, "TESTSUBJECT"),
                new EventType(200, "TESTSUBJECT")
        );
        when(eventTypeService.getAllEventType()).thenReturn(eventTypes);
        List<EventType> result = eventTypeController.getAllEventType();
        assertEquals(eventTypes.size(), result.size());
    }

    @Test
    public void testGetEventTypeById() throws SQLException{
        int id = 1;
        EventType eventType = new EventType(id, "TESTSUBJECT");
        when(eventTypeService.getEventTypeById(id)).thenReturn(eventType);
        EventType result = eventTypeController.getEventTypeById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        String name = "B";
        EventType eventType = new EventType(1, name);
        when(eventTypeService.getEventTypeByName(name)).thenReturn(eventType);
        EventType result = eventTypeController.getEventTypeByName(name);
        assertEquals(name, result.getName());
    }

    @Test
    public void testUpdateEventType_Success() throws SQLException{
        EventType eventType = new EventType(100, "B");
        when(eventTypeService.getEventTypeById(anyInt())).thenReturn(eventType);
        when(eventTypeService.updateEventType(any(EventType.class))).thenReturn(true);
        ResponseEntity<Void> result = eventTypeController.updateEventType(eventType, eventType.getId());
        verify(eventTypeService, times(1)).updateEventType(eventType);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateEventType_Failure() throws SQLException{
        EventType eventType = new EventType(100, "B");
        when(eventTypeService.getEventTypeById(anyInt())).thenReturn(eventType);
        when(eventTypeService.updateEventType(any(EventType.class))).thenReturn(false);
        ResponseEntity<Void> result = eventTypeController.updateEventType(eventType, eventType.getId());
        verify(eventTypeService, times(1)).updateEventType(eventType);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_MODIFIED);
    }

    @Test
    public void testDeleteEventType_Success() throws SQLException{
        EventType eventType = new EventType(100, "B");
        when(eventTypeService.deleteEventType(anyInt())).thenReturn(true);
        ResponseEntity<Void> result = eventTypeController.deleteEventType(eventType.getId());
        verify(eventTypeService, times(1)).deleteEventType(eventType.getId());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteEventType_Failure() throws SQLException{
        EventType eventType = new EventType(100, "B");
        when(eventTypeService.deleteEventType(anyInt())).thenReturn(false);
        ResponseEntity<Void> result = eventTypeController.deleteEventType(eventType.getId());
        verify(eventTypeService, times(1)).deleteEventType(eventType.getId());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_MODIFIED);
    }
}
