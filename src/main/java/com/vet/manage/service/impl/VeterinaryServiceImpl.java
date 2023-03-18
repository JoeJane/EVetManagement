package com.vet.manage.service.impl;

import com.vet.manage.model.entity.Veterinarian;
import com.vet.manage.repository.VeterinaryRepository;
import com.vet.manage.service.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ServiceImpl for VeterinaryService
 * @author Jaswinder
 */
@Service
public class VeterinaryServiceImpl implements VeterinaryService {

    @Autowired
    private VeterinaryRepository veterinaryRepository;

    /**
     * get veterianarian by name
     * @param name
     * @return
     */
    public List<Veterinarian> findByVeterinaryNameContaining(String name){
        return veterinaryRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }
}
