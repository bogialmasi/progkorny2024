package org.nye.progkorny.repository;

import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.processing.SQL;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testGetAllUser() throws SQLException{
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        when(userRepository.getAllUser()).thenReturn(expectedUsers);
        List<User> actualUsers = userRepository.getAllUser();
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).getAllUser();
    }
}
