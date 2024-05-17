package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.Event;
import org.nye.progkorny.repository.impl.DriverManagerFactory;
import org.nye.progkorny.repository.impl.EventRepository;
import org.nye.progkorny.repository.impl.GenericDataAccess;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryTest {
    @Mock
    DriverManagerFactory driverManagerFactory;

    @Mock
    Connection connection;

    @Mock
    Statement statement;

    @Mock
    ResultSet resultSet;
    EventRepository eventRepository;
    private Event event;
    @BeforeEach
    public void setUp() throws SQLException {
        eventRepository = new EventRepository(new GenericDataAccess(driverManagerFactory));
        event = new Event(100, Timestamp.from(Instant.now()),
                "TEST", 1, "TEST", 1);
        when(driverManagerFactory.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
    }
    @Test
    public void testInsertEvent_Successful() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertTrue(eventRepository.insertEvent(event));
        verify(statement, times(1)).executeUpdate(anyString());
        verify(driverManagerFactory, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
    }

    @Test
    public void testInsertEvent_Failure() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        boolean result = eventRepository.insertEvent(event);
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testGetAllEvent() throws SQLException {
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(event);
        List<Event> actualEvents = eventRepository.getAllEvent();
        assertEquals(expectedEvents, actualEvents);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetEventById() throws SQLException {
        int id = event.getId();
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Event actualEvent = eventRepository.getEventById(id);
        assertEquals(event, actualEvent);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetEventByName() throws SQLException {
        String name = event.getName();
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Event actualEvent = eventRepository.getEventByName(name);
        assertEquals(event, actualEvent);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetEventByLocation() throws SQLException {
        String location = event.getLocation();
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Event actualEvent = eventRepository.getEventByLocation(location);
        assertEquals(event, actualEvent);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetEventByUserId() throws SQLException {
        int userid = event.getUserId();
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(event);
        List<Event> actualEvents = eventRepository.getEventByUserId(userid);
        assertEquals(expectedEvents, actualEvents);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetEventByDatetime() throws SQLException {
        Timestamp datetime = Timestamp.from(Instant.now());
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Event actualEvent = eventRepository.getEventByDateTime(datetime);
        assertEquals(event, actualEvent);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetEventByEventTypeId() throws SQLException {
        int eventTypeId = event.getEventTypeId();
        setupEventMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(event);
        List<Event> actualEvents = eventRepository.getEventByEventTypeId(eventTypeId);
        assertEquals(expectedEvents, actualEvents);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testUpdateEvent_Successful() throws SQLException {
        Event event = new Event(100, Timestamp.from(Instant.now()),
                "UPDATE", 1, "UPDATE", 1);
        when(statement.executeUpdate(anyString())).thenReturn(1);
        boolean result = eventRepository.updateEvent(event);
        assertTrue(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testUpdateEvent_Failure() throws SQLException {
        Event event = new Event(100, Timestamp.from(Instant.now()),
                "UPDATE", 1, "UPDATE", 1);
        when(statement.executeUpdate(anyString())).thenReturn(0);
        boolean result = eventRepository.updateEvent(event);
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testDeleteEvent_Successful() throws SQLException {
        int id = 1;
        when(statement.execute(anyString())).thenReturn(true);
        boolean result = eventRepository.deleteEvent(id);
        assertFalse(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testDeleteEvent_Failure() throws SQLException {
        int id = 1;
        when(statement.execute(anyString())).thenReturn(false);
        boolean result = eventRepository.deleteEvent(id);
        assertTrue(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }


    private void verifyConnectionSteps() throws SQLException {
        verify(driverManagerFactory, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
    }

    private void setupEventMap() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(event.getId());
        when(resultSet.getTimestamp(2)).thenReturn(event.getDatetime());
        when(resultSet.getString(3)).thenReturn(event.getLocation());
        when(resultSet.getInt(4)).thenReturn(event.getEventTypeId());
        when(resultSet.getString(5)).thenReturn(event.getName());
        when(resultSet.getInt(6)).thenReturn(event.getUserId());
    }
}
