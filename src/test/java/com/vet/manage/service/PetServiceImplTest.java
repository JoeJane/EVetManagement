package com.vet.manage.service;


import com.vet.manage.model.dto.PetType;
import com.vet.manage.model.entity.Pet;
import com.vet.manage.repository.PetRepository;
import com.vet.manage.service.impl.PetServiceImpl;
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
public class PetServiceImplTest {

    @InjectMocks
    private PetServiceImpl petService;

    @Mock
    private PetRepository petRepository;

    private Pet testPet;

    @BeforeEach
    public void setUp() {
        testPet = new Pet();
        testPet.setId(1);
        testPet.setName("Buddy");
        testPet.setType(PetType.DOG);
        testPet.setBreed("Labrador");
    }

    @Test
    public void testFindById() {
        when(petRepository.findById(any(Integer.class))).thenReturn(Optional.of(testPet));

        Optional<Pet> result = petService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(testPet, result.get());
    }

    @Test
    public void testFindAll() {
        List<Pet> pets = new ArrayList<>();
        pets.add(testPet);

        when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testPet, result.get(0));
    }

}
