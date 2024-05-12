package org.nye.progkorny.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.User;
import org.nye.progkorny.repository.impl.UserRepository;
import org.nye.progkorny.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @Test
    public void testInsertUser(){
        User user = new User(100, "TESTSUBJECT");
        when(userService.addUser(user)).thenReturn(true);
        UserController userController = new UserController(userService);
        ResponseEntity<Void> result = userController.insertUser(user);
        verify(userService, times(1)).addUser(user);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void testGetAllUser() throws SQLException {
        List<User> users = Arrays.asList(
                new User(100, "TESTSUBJECT"),
                new User(100, "TESTSUBJECT")
        );
        when(userService.getAllUser()).thenReturn(users);
        UserController userController = new UserController(userService);
        List<User> result = userController.getAllUser();
        assertEquals(users.size(), result.size());
    }

    @Test
    public void testGetUserById() throws SQLException{
        int id = 1;
        User user = new User(id, "TESTSUBJECT");
        when(userService.getUserById(id)).thenReturn(user);
        UserController userController = new UserController(userService);
        User result = userController.getUserById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetUserByName() throws SQLException {
        String name = "B";
        User user = new User(1, name);
        when(userService.getUserByName(name)).thenReturn(user);
        UserController userController = new UserController(userService);
        User result = userController.getUserByName(name);
        assertEquals(name, result.getName());
    }

}
