package com.vet.manage.repository;

import com.vet.manage.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Repository for Pet Owner
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query(value = "SELECT e FROM Owner as e WHERE (:inputString is null or e.lastName like %:inputString% " +
            "or e.firstName like %:inputString% " +
            "or e.address.street like %:inputString% or e.address.city like %:inputString% " +
            "or concat(e.address.province, '') like %:inputString% or e.address.postalCode like %:inputString% " +
            "or e.phoneNumber like %:inputString% " +
            "or e.address.country like %:inputString% or e.gender like %:inputString% ) "
    )
    public List<Owner> findOwners(String inputString);

    @Query("SELECT DISTINCT o " +
            "FROM Owner o " +
            "JOIN FETCH o.pet p " +
            "LEFT JOIN FETCH p.appointments a " +
            "WHERE a.id IN (SELECT MAX(a2.id) FROM Appointment a2 WHERE a2.pet = p) " +
            "ORDER BY o.id")
    public List<Owner> findOwnersWithLatestAppointment();

    public boolean existsByFirstNameAndLastName(String firstName, String lastName);

    public boolean existsByEmail(String email);

}
