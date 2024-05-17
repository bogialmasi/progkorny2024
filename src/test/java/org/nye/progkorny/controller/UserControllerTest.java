package org.nye.progkorny.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nye.progkorny.model.User;
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

    private UserController userController;
    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp(){
        userController = new UserController(userService);
    }

    @Test
    public void testInsertUser_Success() throws SQLException {
        User user = new User(100, "TESTSUBJECT");
        when(userService.addUser(user)).thenReturn(true);
        ResponseEntity<Void> result = userController.insertUser(user);
        verify(userService, times(1)).addUser(user);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void testInsertUser_Failure() throws SQLException {
        User user = new User(100, "TESTSUBJECT");
        when(userService.addUser(user)).thenReturn(false);
        ResponseEntity<Void> result = userController.insertUser(user);
        verify(userService, times(1)).addUser(user);
        assertEquals(HttpStatus.NOT_MODIFIED, result.getStatusCode());
    }

    @Test
    public void testGetAllUser() throws SQLException {
        List<User> users = Arrays.asList(
                new User(100, "TESTSUBJECT"),
                new User(100, "TESTSUBJECT")
        );
        when(userService.getAllUser()).thenReturn(users);
        List<User> result = userController.getAllUser();
        assertEquals(users.size(), result.size());
    }

    @Test
    public void testGetUserById() throws SQLException{
        int id = 1;
        User user = new User(id, "TESTSUBJECT");
        when(userService.getUserById(id)).thenReturn(user);
        User result = userController.getUserById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetUserByName() throws SQLException {
        String name = "B";
        User user = new User(1, name);
        when(userService.getUserByName(name)).thenReturn(user);
        User result = userController.getUserByName(name);
        assertEquals(name, result.getName());
    }
    @Test
    public void testUpdateUser_Success() throws SQLException{
        User user = new User(100, "B");
        when(userService.getUserById(anyInt())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(true);
        ResponseEntity<Void> result = userController.updateUser(user, user.getId());
        verify(userService, times(1)).updateUser(user);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateUser_Failure() throws SQLException{
        User user = new User(100, "B");
        when(userService.getUserById(anyInt())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(false);
        ResponseEntity<Void> result = userController.updateUser(user, user.getId());
        verify(userService, times(1)).updateUser(user);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_MODIFIED);
    }

    @Test
    public void testDeleteUser_Success() throws SQLException{
        User user = new User(100, "B");
        when(userService.deleteUser(anyInt())).thenReturn(true);
        ResponseEntity<Void> result = userController.deleteUser(user.getId());
        verify(userService, times(1)).deleteUser(user.getId());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteUser_Failure() throws SQLException{
        User user = new User(100, "B");
        when(userService.deleteUser(anyInt())).thenReturn(false);
        ResponseEntity<Void> result = userController.deleteUser(user.getId());
        verify(userService, times(1)).deleteUser(user.getId());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_MODIFIED);
    }
}
