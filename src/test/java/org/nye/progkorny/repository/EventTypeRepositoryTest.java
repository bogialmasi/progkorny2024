package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.EventType;
import org.nye.progkorny.repository.impl.DriverManagerFactory;
import org.nye.progkorny.repository.impl.EventTypeRepository;
import org.nye.progkorny.repository.impl.GenericDataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventTypeRepositoryTest {

    @Mock
    DriverManagerFactory driverManagerFactory;

    @Mock
    Connection connection;

    @Mock
    Statement statement;

    @Mock
    ResultSet resultSet;

    EventTypeRepository eventTypeRepository;
    private EventType eventType;

    @BeforeEach
    public void setUp() throws SQLException {
        eventTypeRepository = new EventTypeRepository(new GenericDataAccess(driverManagerFactory));
        eventType = new EventType(100, "B");


        when(driverManagerFactory.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
    }
    @Test
    public void testInsertEventType_Successful() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertTrue(eventTypeRepository.insertEventType(eventType));
        verify(statement, times(1)).executeUpdate(anyString());
        verify(driverManagerFactory, times(1)).getConnection();
        verify(connection, times(1)).createStatement();

    }
    @Test
    public void testInsertEventType_Failure() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        boolean result = eventTypeRepository.insertEventType(eventType);
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testGetAllEventType() throws SQLException {
        setupEventTypeMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        List<EventType> expectedEventTypes = new ArrayList<>();
        expectedEventTypes.add(eventType);
        List<EventType> actualEventTypes = eventTypeRepository.getAllEventType();
        assertEquals(expectedEventTypes, actualEventTypes);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();

    }
    @Test
    public void testGetEventTypeById() throws SQLException {
        int id = eventType.getId();
        setupEventTypeMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        EventType actualEventType = eventTypeRepository.getEventTypeById(id);
        assertEquals(eventType, actualEventType);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();

    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        String name = eventType.getName();
        setupEventTypeMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        EventType actualEventType = eventTypeRepository.getEventTypeByName(name);
        assertEquals(eventType, actualEventType);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testUpdateEventType_Successful() throws SQLException {
        EventType eventType = new EventType(200, "BC");
        when(statement.executeUpdate(anyString())).thenReturn(1);
        boolean result = eventTypeRepository.updateEventType(eventType);
        assertTrue(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testUpdateEventType_Failure() throws SQLException {
        EventType eventType = new EventType(200, "BC");
        when(statement.executeUpdate(anyString())).thenReturn(0);
        boolean result = eventTypeRepository.updateEventType(eventType);
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testDeleteEventType_Successful() throws SQLException {
        int id = 1;
        when(statement.execute(anyString())).thenReturn(true);
        boolean result = eventTypeRepository.deleteEventType(id);
        assertFalse(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testDeleteEventType_Failure() throws SQLException {
        int id = 1;
        when(statement.execute(anyString())).thenReturn(false);
        boolean result = eventTypeRepository.deleteEventType(id);
        assertTrue(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }

    private void verifyConnectionSteps() throws SQLException {
        verify(driverManagerFactory, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
    }

    private void setupEventTypeMap() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(eventType.getId());
        when(resultSet.getString(2)).thenReturn(eventType.getName());
    }
}
