package org.nye.progkorny.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.UserRepository;
import org.nye.progkorny.service.impl.UserService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        userService = new UserService(userRepository);
    }
    @Test
    public void testInsertUser_Success() throws SQLException {
        User user = new User(100, "TESTSUBJECT");
        when(userRepository.insertUser(user)).thenReturn(true);
        boolean result = userService.addUser(user);
        verify(userRepository, times(1)).insertUser(user);
        assertTrue(result);
    }
    @Test
    public void testInsertUser_Failure() throws SQLException {
        User user = new User(100, "TESTSUBJECT");
        when(userRepository.insertUser(user)).thenReturn(false);
        boolean result = userService.addUser(user);
        verify(userRepository, times(1)).insertUser(user);
        assertFalse(result);
    }
    @Test
    public void testGetAllUser() throws SQLException {
        List<User> users = Arrays.asList(
                new User(100, "TESTSUBJECT"),
                new User(100, "TESTSUBJECT")
        );
        when(userRepository.getAllUser()).thenReturn(users);
        List<User> result = userService.getAllUser();
        assertEquals(users.size(), result.size());
    }

    @Test
    public void testGetUserById() throws SQLException{
        int id = 1;
        User user = new User(id, "TESTSUBJECT");
        when(userRepository.getUserById(id)).thenReturn(user);
        User result = userService.getUserById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetUserByName() throws SQLException {
        String name = "B";
        User user = new User(1, name);
        when(userRepository.getUserByName(name)).thenReturn(user);
        User result = userRepository.getUserByName(name);
        assertEquals(name, result.getName());
    }
    @Test
    public void testUpdateUser_Success() throws SQLException{
        when(userRepository.updateUser(any(User.class))).thenReturn(true);
        UserService userService = new UserService(userRepository);
        boolean result = userService.updateUser(new User(1, "TESTSUBJECT"));
        assertTrue(result);
    }
    @Test
    public void testUpdateUser_Failure() throws SQLException{
        when(userRepository.updateUser(any(User.class))).thenReturn(false);
        UserService userService = new UserService(userRepository);
        boolean result = userService.updateUser(new User(1, "TESTSUBJECT"));
        assertFalse(result);
    }
    @Test
    public void testDeleteUser_Success() throws SQLException{
        int id = 1;
        when(userRepository.deleteUser(id)).thenReturn(true);
        UserService userService = new UserService(userRepository);
        boolean result = userService.deleteUser(id);
        assertTrue(result);
    }
    @Test
    public void testDeleteUser_Failure() throws SQLException{
        int id = 1;
        when(userRepository.deleteUser(id)).thenReturn(false);
        UserService userService = new UserService(userRepository);
        boolean result = userService.deleteUser(id);
        assertFalse(result);
    }

}
