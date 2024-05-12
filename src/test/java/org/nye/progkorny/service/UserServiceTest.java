package org.nye.progkorny.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.controller.UserController;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.UserRepository;
import org.nye.progkorny.service.impl.UserService;
import org.springframework.data.repository.cdi.Eager;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testInsertUser(){
        User user = new User(100, "TESTSUBJECT");
        when(userRepository.insertUser(user)).thenReturn(true);
        UserService userService = new UserService(userRepository);
        boolean result = userService.addUser(user);
        assertTrue(result);
        verify(userRepository, times(1)).insertUser(user);
    }

    @Test
    public void testGetAllUser() throws SQLException {
        List<User> users = Arrays.asList(
                new User(100, "TESTSUBJECT"),
                new User(100, "TESTSUBJECT")
        );
        when(userRepository.getAllUser()).thenReturn(users);
        UserService userService = new UserService(userRepository);
        List<User> result = userService.getAllUser();
        assertEquals(users.size(), result.size());
    }

    @Test
    public void testGetUserById() throws SQLException{
        int id = 1;
        User user = new User(id, "TESTSUBJECT");
        when(userRepository.getUserById(id)).thenReturn(user);
        UserService userService = new UserService(userRepository);
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

}
