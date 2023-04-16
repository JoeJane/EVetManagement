package com.vet.manage.service;


import com.vet.manage.model.entity.Clinic;

import java.util.List;
import java.util.Optional;

/**
 * Service for Clinic
 */
public interface ClinicService {
    public void saveOrUpdate(Clinic clinic);
    public Optional<Clinic> findById(Integer id);

    public List<Clinic> findAll();

    public void changeStatusById(Integer id, boolean status);

}
