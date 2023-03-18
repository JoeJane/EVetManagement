package com.vet.manage.repository;

import com.vet.manage.model.entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Veterinarian
 * author: Josef
 */
public interface VeterinaryRepository extends JpaRepository<Veterinarian, Integer> {

    public List<Veterinarian> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

}
