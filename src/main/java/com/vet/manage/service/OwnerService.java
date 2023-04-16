package com.vet.manage.service;

import com.vet.manage.model.entity.Owner;

import java.util.List;
import java.util.Optional;

/**
 * Service for Owner
 */
public interface OwnerService {

    public Optional<Owner> findById(Integer id);

    public List<Owner> findAll();

    public void saveAllOwner(List<Integer> ids, String status);

    public void saveOrUpdate(Owner owner);

    public void changeStatusById(Integer id, boolean status);

    public List<Owner> search(String searchTerm);

    public List<Owner> findOwnersWithLatestAppointment();

    public boolean existsByFirstNameAndLastName(String firstName, String lastName);

    public boolean existsByEmail(String email);

}
