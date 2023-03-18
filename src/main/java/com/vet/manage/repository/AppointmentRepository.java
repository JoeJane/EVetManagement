package com.vet.manage.repository;

import com.vet.manage.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


/**
 * Repository for Appointment
 * @author Jaswinder
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a WHERE a.pet.id = :pet_id AND a.appointmentDate = (SELECT MAX(appointmentDate) FROM Appointment WHERE pet.id = :pet_id)")
    Optional<Appointment> findLatestAppointment(Integer pet_id);
}
