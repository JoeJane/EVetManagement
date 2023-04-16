package com.vet.manage.service;


import com.vet.manage.model.entity.Clinic;
import com.vet.manage.repository.ClinicRepository;
import com.vet.manage.service.impl.ClinicServiceImpl;
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
public class ClinicServiceImplTest {

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @Mock
    private ClinicRepository clinicRepository;

    private Clinic testClinic;

    @BeforeEach
    public void setUp() {
        testClinic = new Clinic();
        testClinic.setId(1);
        testClinic.setName("Test Clinic");
    }

    @Test
    public void testSaveOrUpdate() {
        when(clinicRepository.save(any(Clinic.class))).thenReturn(testClinic);

        clinicService.saveOrUpdate(testClinic);

        verify(clinicRepository, times(1)).save(testClinic);
    }

    @Test
    public void testFindById() {
        when(clinicRepository.findById(any(Integer.class))).thenReturn(Optional.of(testClinic));

        Optional<Clinic> result = clinicService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(testClinic, result.get());
    }

    @Test
    public void testFindAll() {
        List<Clinic> clinics = new ArrayList<>();
        clinics.add(testClinic);

        when(clinicRepository.findAll()).thenReturn(clinics);

        List<Clinic> result = clinicService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testClinic, result.get(0));
    }

    @Test
    public void testChangeStatusById() {
        when(clinicRepository.findById(any(Integer.class))).thenReturn(Optional.of(testClinic));
        when(clinicRepository.save(any(Clinic.class))).thenReturn(testClinic);

        clinicService.changeStatusById(1, false);

        verify(clinicRepository, times(1)).save(testClinic);
    }
}
