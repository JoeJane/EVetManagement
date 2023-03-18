package com.vet.manage.repository;

import com.vet.manage.model.dto.PetType;
import com.vet.manage.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Repository for Pet
 * @author Maiara Almeida
 */
public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query(value = "SELECT p FROM Pet as p WHERE (:inputString is null or p.name like %:inputString% " +
            "or p.type = :type " +
            "or p.breed like %:breed% " +
            "or p.owner.firstName like %:inputString% " +
            "or p.owner.lastName like %:inputString% ) "
    )
    public List<Pet> findPetBy(String inputString, PetType type, String breed);

    @Query(value = "SELECT p FROM Pet as p WHERE (:inputString is null or p.name like %:inputString% " +
            "or p.breed like %:inputString% " +
            "or p.owner.firstName like %:inputString% " +
            "or p.owner.lastName like %:inputString% ) "
    )
    public List<Pet> findPetBy(String inputString);

}
