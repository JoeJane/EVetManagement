package com.vet.manage.service;

import com.vet.manage.model.entity.Owner;
import com.vet.manage.repository.OwnerRepository;
import com.vet.manage.service.impl.OwnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    private Owner testOwner;

    @BeforeEach
    public void setUp() {
        testOwner = new Owner();
        testOwner.setUserId(1);
        testOwner.setFirstName("John");
        testOwner.setLastName("Doe");
        testOwner.setEmail("john.doe@example.com");
    }

    @Test
    public void testFindById() {
        when(ownerRepository.findById(any(Integer.class))).thenReturn(Optional.of(testOwner));

        Optional<Owner> result = ownerService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(testOwner, result.get());
    }

    @Test
    public void testFindAll() {
        List<Owner> owners = new ArrayList<>();
        owners.add(testOwner);

        when(ownerRepository.findAll()).thenReturn(owners);

        List<Owner> result = ownerService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOwner, result.get(0));
    }

}