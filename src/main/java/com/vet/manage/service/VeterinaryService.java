package com.vet.manage.service;


import com.vet.manage.model.entity.Veterinarian;

import java.util.List;

/**
 * Service for Veterinarian
 */
public interface VeterinaryService {

    public List<Veterinarian> findByVeterinaryNameContaining(String name);

}
