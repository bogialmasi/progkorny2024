package org.nye.progkorny.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    UserRepository userRepository;
    private User user;
    @BeforeEach
    public void setUp(){
        user = new User(100, "B");
    }

    // C
    @Test
    public void testInsertUser_Successful() throws SQLException {
        when(userRepository.insertUser(user)).thenReturn(true);
        assertTrue(userRepository.insertUser(user));
        verify(userRepository, times(1)).insertUser(user);
    }

    @Test
    public void testInsertUser_Failure() throws SQLException {
        when(userRepository.insertUser(user)).thenReturn(false);
        assertFalse(userRepository.insertUser(user));
        verify(userRepository, times(1)).insertUser(user);
    }

    // R
    @Test
    public void testGetAllUser() throws SQLException{
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        when(userRepository.getAllUser()).thenReturn(expectedUsers);
        List<User> actualUsers = userRepository.getAllUser();
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).getAllUser();
    }

    @Test
    public void testGetUserById() throws SQLException {
        int id = user.getId();
        when(userRepository.getUserById(id)).thenReturn(user);
        User actualUser = userRepository.getUserById(id);
        assertEquals(user, actualUser);
        verify(userRepository, times(1)).getUserById(id);
    }

    @Test
    public void testGetUserByName() throws SQLException {
        String name = user.getName();
        when(userRepository.getUserByName(name)).thenReturn(user);
        User actualUser = userRepository.getUserByName(name);
        assertEquals(user, actualUser);
        verify(userRepository, times(1)).getUserByName(name);
    }

    // U

    @Test
    public void testUpdateUser_Successful() throws SQLException {
        UserRepository userRepository = mock(UserRepository.class);
        User user = new User(200, "BC");
        when(userRepository.updateUser(user)).thenReturn(true);
        assertTrue(userRepository.updateUser(user));
        verify(userRepository, times(1)).updateUser(user);
    }

    @Test
    public void testUpdateUser_Failure() throws SQLException {
        UserRepository userRepository = mock(UserRepository.class);
        User user = new User(200, "BC");
        when(userRepository.updateUser(user)).thenReturn(false);
        assertFalse(userRepository.updateUser(user));
        verify(userRepository, times(1)).updateUser(user);
    }

    // D

    @Test
    public void testDeleteUser_Successful(){
        UserRepository userRepository = mock(UserRepository.class);
        int id = 1;
        when(userRepository.deleteUser(id)).thenReturn(true);
        assertTrue(userRepository.deleteUser(id));
        verify(userRepository, times(1)).deleteUser(id);
    }

    @Test
    public void testDeleteUser_Failure(){
        UserRepository userRepository = mock(UserRepository.class);
        int id = 1;
        when(userRepository.deleteUser(id)).thenReturn(false);
        assertFalse(userRepository.deleteUser(id));
        verify(userRepository, times(1)).deleteUser(id);
    }

}
