package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.DriverManagerFactory;
import org.nye.progkorny.repository.impl.GenericDataAccess;
import org.nye.progkorny.repository.impl.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {


    @Mock
    DriverManagerFactory driverManagerFactory;

    @Mock
    Connection connection;

    @Mock
    Statement statement;

    @Mock
    ResultSet resultSet;

    UserRepository userRepository;
    private User user;
    @BeforeEach
    public void setUp() throws SQLException {
        userRepository = new UserRepository(new GenericDataAccess(driverManagerFactory));
        user = new User(100, "B");

        when(driverManagerFactory.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
    }

    @Test
    public void testInsertUser_Successful() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertTrue(userRepository.insertUser(user));
        verify(statement, times(1)).executeUpdate(anyString());
        verify(driverManagerFactory, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
    }

    @Test
    public void testInsertUser_Failure() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        boolean result = userRepository.insertUser(user);
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testGetAllUser() throws SQLException{
       setupUserMap();
       when(statement.executeQuery(anyString())).thenReturn(resultSet);
       List<User> expectedUsers = new ArrayList<>();
       expectedUsers.add(user);
       List<User> actualUsers = userRepository.getAllUser();
       assertEquals(expectedUsers, actualUsers);
       verify(statement, times(1)).executeQuery(anyString());
       verifyConnectionSteps();
    }

    @Test
    public void testGetUserById() throws SQLException {
        int id = user.getId();
        setupUserMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        User actualUser = userRepository.getUserById(id);
        assertEquals(user, actualUser);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testGetUserByName() throws SQLException {
        String name = user.getName();
        setupUserMap();
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        User actualUser = userRepository.getUserByName(name);
        assertEquals(user, actualUser);
        verify(statement, times(1)).executeQuery(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testUpdateUser_Successful() throws SQLException {
        User user = new User(200, "BC");
        when(statement.executeUpdate(anyString())).thenReturn(1);
        boolean result = userRepository.updateUser(user);
        assertTrue(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testUpdateUser_Failure() throws SQLException {
        User user = new User(200, "BC");
        when(statement.executeUpdate(anyString())).thenReturn(0);
        boolean result = userRepository.updateUser(user);
        assertFalse(result);
        verify(statement, times(1)).executeUpdate(anyString());
        verifyConnectionSteps();
    }
    @Test
    public void testDeleteUser_Successful() throws SQLException {
        int id = 1;
        when(statement.execute(anyString())).thenReturn(true);
        boolean result = userRepository.deleteUser(id);
        assertFalse(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }

    @Test
    public void testDeleteUser_Failure() throws SQLException {
        int id = 1;
        when(statement.execute(anyString())).thenReturn(false);
        boolean result = userRepository.deleteUser(id);
        assertTrue(result);
        verify(statement, times(1)).execute(anyString());
        verifyConnectionSteps();
    }

    private void verifyConnectionSteps() throws SQLException {
        verify(driverManagerFactory, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
    }

    private void setupUserMap() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(user.getId());
        when(resultSet.getString(2)).thenReturn(user.getName());
    }

}
