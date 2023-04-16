package com.vet.manage.service;

import com.vet.manage.model.entity.Veterinarian;
import com.vet.manage.repository.VeterinaryRepository;
import com.vet.manage.service.impl.VeterinaryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VeterinaryServiceImplTest {

    @InjectMocks
    private VeterinaryServiceImpl veterinaryService;

    @Mock
    private VeterinaryRepository veterinarianRepository;

    private Veterinarian testVeterinarian;

    @BeforeEach
    public void setUp() {
        testVeterinarian = new Veterinarian();
        testVeterinarian.setUserId(1);
        testVeterinarian.setFirstName("John");
        testVeterinarian.setLastName("Doe");
    }

    @Test
    public void testFindByVeterinaryNameContaining() {
        List<Veterinarian> veterinarians = new ArrayList<>();
        veterinarians.add(testVeterinarian);

        when(veterinarianRepository.findByFirstNameContainingOrLastNameContaining(anyString(), anyString())).thenReturn(veterinarians);

        List<Veterinarian> result = veterinaryService.findByVeterinaryNameContaining("John");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testVeterinarian.getFirstName(), result.get(0).getFirstName());
        assertEquals(testVeterinarian.getLastName(), result.get(0).getLastName());
    }
}