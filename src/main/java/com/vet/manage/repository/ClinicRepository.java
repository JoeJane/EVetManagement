package com.vet.manage.repository;

import com.vet.manage.model.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for Clinic
 */
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
}
