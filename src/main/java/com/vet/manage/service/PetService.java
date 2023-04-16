package com.vet.manage.service;

import com.vet.manage.model.dto.PetType;
import com.vet.manage.model.entity.Pet;

import java.util.List;
import java.util.Optional;

/**
 * Service for Pet
 * @author Jaswinder
 */
public interface PetService {

    public Optional<Pet> findById(Integer id);

    public List<Pet> findPetBy(String inputString, PetType type, String breed);
    public List<Pet> findPetBy(String inputString);

    public List<Pet> findAll();

    public List<Pet> search(String searchTerm);

    public List<Pet> findPetByVeterinarian(Integer userId);

    public List<Pet> findPetByVeterinarian(String inputString, Integer userId);

}
