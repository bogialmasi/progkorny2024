package org.nye.progkorny.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.controller.EventTypeController;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.impl.EventTypeRepository;
import org.nye.progkorny.service.impl.EventTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventTypeServiceTest {

    @Mock
    private EventTypeRepository eventTypeRepository;

    // C

    @Test
    public void testInsertEventType(){
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

}
