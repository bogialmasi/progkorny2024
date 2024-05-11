package org.nye.progkorny.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.UserRepository;
import org.nye.progkorny.service.UserServiceTest;
import org.nye.progkorny.service.impl.UserService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddUser(){
        User user = new User(100, "TESTSUBJECT");
        when(userRepository.insertUser(user)).thenReturn(true);
        UserService userService = new UserService(userRepository);
        boolean result = userService.addUser(user);
        assertTrue(result);
        verify(userRepository, times(1)).insertUser(user);
    }

    @Test
    public void testGetAllUser() throws SQLException{
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
        assertEquals(id, user.getId());
    }
}
