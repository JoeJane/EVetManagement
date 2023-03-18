package com.vet.manage.service;

import com.vet.manage.model.dto.Status;
import com.vet.manage.model.entity.Appointment;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Service for Appointment
 * @author Maiara Almeida
 */
public interface AppointmentService {
    Optional<Appointment> findLatestAppointment(Integer petID);

    public void saveOrUpdate(Appointment pet);

    public Optional<Appointment> findById(Integer id);

    public void changeStatus(Integer id, Status status);

    public Appointment storeFileByAppointment(MultipartFile file, Integer appointmentId, Integer labAssistantID);

}
