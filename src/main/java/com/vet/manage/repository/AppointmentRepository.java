package com.vet.manage.repository;

import com.vet.manage.model.dto.Status;
import com.vet.manage.model.entity.Appointment;
import com.vet.manage.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


/**
 * Repository for Appointment
 * @author Jaswinder
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a WHERE a.pet.id = :pet_id AND a.appointmentDate = (SELECT MAX(appointmentDate) FROM Appointment WHERE pet.id = :pet_id)")
    Optional<Appointment> findLatestAppointment(Integer pet_id);



    @Query(value = "SELECT a FROM Appointment as a inner join a.veterinarian as v" +
            " WHERE v.userId = :userId and a.pet.id = :petId and a.status = :status")
    public Optional<Appointment> findAppointmentByPetIdAndVetId(Integer petId, Integer userId, Status status);
}
