package com.vet.manage.service;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.entity.User;
import com.vet.manage.repository.UserRepository;
import com.vet.manage.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

/**
 * E-Vet UserService class
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup our mock repository
        User user = new User(1, "admin", "password", Role.ADMIN);
        doReturn(Optional.of(user)).when(repository).findById(1);

        // Execute the service call
        Optional<User> returnedUser = service.findById(1);

        // Assert the response
        Assertions.assertTrue(returnedUser.isPresent(), "User was not found");
        Assertions.assertSame(returnedUser.get(), user, "The User returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(repository).findById(1);

        // Execute the service call
        Optional<User> returnedUser = service.findById(1);

        // Assert the response
        Assertions.assertFalse(returnedUser.isPresent(), "User should not be found");
    }

    @Test
    @DisplayName("Test findUserByUsername Success")
    void testFindUserByUsername() {
        // Setup our mock repository
        User user = new User(1, "admin", "password", Role.ADMIN);
        doReturn(Optional.of(user)).when(repository).findUserByUsername("admin");

        // Execute the service call
        Optional<User> returnedUser = service.findUserByUsername("admin");

        // Assert the response
        Assertions.assertTrue(returnedUser.isPresent(), "User was not found");
        Assertions.assertSame(returnedUser.get(), user, "The User returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findUserByUsername Not Found")
    void testFindUserByUsernameNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(repository).findUserByUsername("admin");

        // Execute the service call
        Optional<User> returnedUser = service.findUserByUsername("admin");

        // Assert the response
        Assertions.assertFalse(returnedUser.isPresent(), "User should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        User user1 = new User(1, "admin", "password", Role.ADMIN);
        User user2 = new User(2, "jane", "password", Role.RECEPTIONIST);
        doReturn(Arrays.asList(user1, user2)).when(repository).findAll();

        // Execute the service call
        List<User> users = service.findAll();

        // Assert the response
        Assertions.assertEquals(2, users.size(), "findAll should return 2 users");
    }

    @Test
    @DisplayName("Test findByRole")
    void testFindByRole() {
        // Setup our mock repository
        User user1 = new User(1, "admin", "password", Role.LAB_ASSISTANT);
        User user2 = new User(2, "jane", "password", Role.LAB_ASSISTANT);
        doReturn(Arrays.asList(user1, user2)).when(repository).findByRole(Role.LAB_ASSISTANT);

        // Execute the service call
        List<User> users = service.findByRole(Role.LAB_ASSISTANT);

        // Assert the response
        Assertions.assertEquals(2, users.size(), "findByRole should return 2 users");
    }

    @Test
    @DisplayName("Test findByRoleNot")
    void testFindByRoleNot() {
        // Setup our mock repository
        User user1 = new User(1, "admin", "password", Role.LAB_ASSISTANT);
        User user2 = new User(2, "jane", "password", Role.ADMIN);
        doReturn(Arrays.asList(user1)).when(repository).findByRole(Role.ADMIN);

        // Execute the service call
        List<User> users = service.findByRole(Role.ADMIN);

        // Assert the response
        Assertions.assertEquals(1, users.size(), "findByRole should return 2 users");
    }
}
