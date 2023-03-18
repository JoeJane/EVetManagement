package com.vet.manage.service.impl;

import com.vet.manage.model.entity.Address;
import com.vet.manage.model.entity.Clinic;
import com.vet.manage.repository.ClinicRepository;
import com.vet.manage.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * ServiceImpl for Clinic
 * @author Maiara Almeida
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Optional<Clinic> findById(Integer id) {
        return clinicRepository.findById(id);
    }

    /**
     * savee or update clinic
     * @param clinic
     */
    @Override
    public void saveOrUpdate(Clinic clinic) {
        Integer clinicId = clinic.getId();

        Optional<Clinic> clinicData = clinicId == null ? Optional.empty() : findById(clinicId);

        if (clinicData.isPresent()) {
            Clinic existingClinic = clinicData.get();

            Address existingAddress = existingClinic.getAddress();
            Address editedAddress = clinic.getAddress();

            existingAddress.setStreet(editedAddress.getStreet());
            existingAddress.setAddressNo(editedAddress.getAddressNo());
            existingAddress.setCity(editedAddress.getCity());
            existingAddress.setPostalCode(editedAddress.getPostalCode());
            existingAddress.setProvince(editedAddress.getProvince());
            existingAddress.setCountry(editedAddress.getCountry());

            clinicRepository.save(existingClinic);
        } else {
            clinicRepository.save(clinic);
        }
    }

    /**
     * get all clinics
     * @return
     */
    public List<Clinic> findAll(){
        return clinicRepository.findAll();
    }

    /**
     * change clinic status
     * @param id clinic id
     * @param status status
     */
    public void changeStatusById(Integer id, boolean status){
        Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinic not found!"));
        clinic.setDeleted(status);
        clinicRepository.save(clinic);
    }
}
