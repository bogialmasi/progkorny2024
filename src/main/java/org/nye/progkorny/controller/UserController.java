package org.nye.progkorny.controller;

import org.nye.progkorny.model.User;
import org.nye.progkorny.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // C
    @PostMapping(path = "/")
    public ResponseEntity<Void> InsertUSer(@RequestBody User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

    // R

    @GetMapping(path = "/")
    public List<User> getAllUser() throws SQLException {
        return userService.getAllUser();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id) throws SQLException {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/name", params = "name")
    public User getUserByName(@RequestParam String name) throws SQLException {
        return userService.getUserByName(name);
    }

    // U

    @PutMapping(path = "/", params = "id")
    public ResponseEntity<Void> updateUser(@RequestBody User newUser, @RequestParam int id) throws SQLException {
        User user = userService.getUserById(id);
        user.setName(newUser.getName());
        if(userService.updateUser(user)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

    // D

    @DeleteMapping(path = "/", params = "id")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") int id){
        boolean result = userService.deleteUser(id);
        if(result){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

}
