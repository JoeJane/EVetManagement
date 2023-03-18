package com.vet.manage.service.impl;

import com.vet.manage.model.dto.PetType;
import com.vet.manage.model.entity.Pet;
import com.vet.manage.repository.PetRepository;
import com.vet.manage.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * ServiceImpl for Pet Service
 * @author Josef
 */
@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    /**
     * Load owner based on ID
     * @param id Owner id
     * @return Owner
     */
    @Override
    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    /**
     * search pet by search term, type and bread
     * @param inputString
     * @param type
     * @param breed
     * @return
     */
    @Override
    public List<Pet> findPetBy(String inputString, PetType type, String breed) {
        return petRepository.findPetBy(inputString, type, breed);
    }

    /**
     * search pet by input string
     * @param inputString
     * @return
     */
    @Override
    public List<Pet> findPetBy(String inputString) {
        return petRepository.findPetBy(inputString);
    }


    /**
     * Load all owners
     * @return list of owners
     */
    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }


    /**
     * search pet by search term
     * @param term
     * @return
     */
    @Override
    public List<Pet> search(String term) {
        return petRepository.findPetBy(term);
    }

}
