package com.vet.manage.service.impl;

import com.vet.manage.model.entity.Address;
import com.vet.manage.model.entity.Owner;
import com.vet.manage.model.entity.Pet;
import com.vet.manage.repository.OwnerRepository;
import com.vet.manage.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * ServiceImpl for Pet owner
 * @author abasibianke
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Load owner based on ID
     * @param id Owner id
     * @return Owner
     */
    @Override
    public Optional<Owner> findById(Integer id) {
        return ownerRepository.findById(id);
    }


    /**
     * Load all owners
     * @return list of owners
     */
    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }



    /**
     * Update multiple owners
     * @param ids Owner ids
     * @param status Status
     */
    @Override
    public void saveAllOwner(List<Integer> ids, String status) {
        List<Owner> owners = ownerRepository.findAllById(ids);
        boolean isDeleted = status.equals("1") ? false : true;

        ownerRepository.saveAll(owners);
    }
    
    /**
     * Save or update owner
     * @param owner Owner entity
     */
    @Override
    public void saveOrUpdate(Owner owner) {
        Integer ownerId = owner.getUserId();

        Optional<Owner> ownerData = ownerId == null ? Optional.empty() : findById(ownerId);

        if (ownerData.isPresent()) {
            Owner ownerTemp = ownerData.get();

            ownerTemp.setFirstName(owner.getFirstName());
            ownerTemp.setLastName(owner.getLastName());
            ownerTemp.setGender(owner.getGender());
            ownerTemp.setEmail(owner.getEmail());
            ownerTemp.setPhoneNumber(owner.getPhoneNumber());

            Address addressDB = ownerTemp.getAddress();
            Address formAddress = owner.getAddress();

            addressDB.setStreet(formAddress.getStreet());
            addressDB.setAddressNo(formAddress.getAddressNo());
            addressDB.setCity(formAddress.getCity());
            addressDB.setProvince(formAddress.getProvince());
            addressDB.setPostalCode(formAddress.getPostalCode());
            addressDB.setCountry(formAddress.getCountry());

            Pet petDB = ownerTemp.getPet();
            Pet formPet = owner.getPet();
            petDB.setName(formPet.getName());
            petDB.setType(formPet.getType());
            petDB.setAge(formPet.getAge());
            petDB.setWeight(formPet.getWeight());

            ownerRepository.save(ownerTemp);
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(owner.getPassword());
            owner.setPassword(encodedPassword);

            owner.getPet().setOwner(owner);
            ownerRepository.save(owner);
        }
    }

    /**
     * Change owner status
     * @param id Owner ID
     * @param status Status
     */
    @Override
    public void changeStatusById(Integer id, boolean status) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found!"));
        owner.setDeleted(status);
        owner.getPet().setDeleted(status);
        ownerRepository.save(owner);
    }

    /**
     * search owner by search term
     * @param term
     * @return
     */
    @Override
    public List<Owner> search(String term) {
        return ownerRepository.findOwners(term);
    }

    /**
     * find owner with latest appointment
     * @return
     */
    @Override
    public List<Owner> findOwnersWithLatestAppointment(){
        return ownerRepository.findOwnersWithLatestAppointment();
    }

    /**
     * check user exist
     * @param firstName first name
     * @param lastName last name
     * @return
     */
    public boolean existsByFirstNameAndLastName(String firstName, String lastName){
        return ownerRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * check user exist for given email
     * @param email
     * @return
     */
    public boolean existsByEmail(String email){
        return ownerRepository.existsByEmail(email);
    }

}
