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
    Connection conn;

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


        when(driverManagerFactory.getConnection()).thenReturn(conn);
        when(conn.createStatement()).thenReturn(statement);
    }

    // C
    @Test
    public void testInsertEventType_Successful() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertTrue(eventTypeRepository.insertEventType(eventType));
        verify(statement, times(1)).executeUpdate(anyString());
        verify(driverManagerFactory, times(1)).getConnection();
        verify(conn, times(1)).createStatement();

    }

    @Test
    public void testInsertEventType_Failure() throws SQLException {
        //arrange
        when(statement.executeUpdate(anyString())).thenReturn(0);

        //act
        boolean result = eventTypeRepository.insertEventType(eventType);

        //assert
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }


    // R
    @Test
    public void testGetAllEventType() throws SQLException {
        //arrange
        setupEventTypeMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        List<EventType> expectedEventTypes = new ArrayList<>();
        expectedEventTypes.add(eventType);

        //act
        List<EventType> actualEventTypes = eventTypeRepository.getAllEventType();

        //assert
        assertEquals(expectedEventTypes, actualEventTypes);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();

    }



    @Test
    public void testGetEventTypeById() throws SQLException {
        //arrange
        int id = eventType.getId();
        setupEventTypeMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        //act
        EventType actualEventType = eventTypeRepository.getEventTypeById(id);

        //assert
        assertEquals(eventType, actualEventType);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();

    }

    @Test
    public void testGetEventTypeByName() throws SQLException {
        //arrange
        String name = eventType.getName();
        setupEventTypeMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        //act
        EventType actualEventType = eventTypeRepository.getEventTypeByName(name);

        //assert
        assertEquals(eventType, actualEventType);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    // U

    @Test
    public void testUpdateEventType_Successful() throws SQLException {
        //arrange
        EventType eventType = new EventType(200, "BC");
        when(statement.executeUpdate(anyString())).thenReturn(1);

        //act
        var result = eventTypeRepository.updateEventType(eventType);

        assertTrue(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testUpdateEventType_Failure() throws SQLException {
        //arrange
        EventType eventType = new EventType(200, "BC");
        when(statement.executeUpdate(anyString())).thenReturn(0);

        //act
        boolean result = eventTypeRepository.updateEventType(eventType);

        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }

    // D

    @Test
    public void testDeleteEventType_Successful() throws SQLException {
        //arrange
        int id = 1;
        when(statement.execute(anyString())).thenReturn(true);

        //act
        boolean result = eventTypeRepository.deleteEventType(id);

        //assert
        assertFalse(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testDeleteEventType_Failure() throws SQLException {
        //arrange
        int id = 1;
        when(statement.execute(anyString())).thenReturn(false);

        //act
        boolean result = eventTypeRepository.deleteEventType(id);

        //assert
        assertTrue(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }

    private void verifyConnectionSteps() throws SQLException {
        verify(driverManagerFactory, times(1)).getConnection();
        verify(conn, times(1)).createStatement();
    }

    private void setupEventTypeMap() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(eventType.getId());
        when(resultSet.getString(2)).thenReturn(eventType.getName());
    }
}
