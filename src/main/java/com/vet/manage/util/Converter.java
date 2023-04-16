package com.vet.manage.util;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.entity.*;

/**
 * Util class to convert user to appropriate user type
 */
public class Converter {

    /**
     * Convert users into target user based on @Role
     * @param user User entity
     * @return Target user
     */
    public static User convert(User user){
        User newUser;
        if(user.getRole() == Role.VETERINARIAN) {
            newUser = convertToVeterinarian(user, new Veterinarian());
        } else if(user.getRole() == Role.LAB_ASSISTANT) {
            newUser = convertToLabAssistant(user, new LabAssistant());
        } else if(user.getRole() == Role.RECEPTIONIST) {
            newUser = convertToReceptionist(user, new Receptionist());
        } else {
            newUser = user;
        }

        newUser.setUserId(user.getUserId());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setConfirmPassword(user.getConfirmPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setAddress(user.getAddress());
        newUser.setGender(user.getGender());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setDeleted(user.getDeleted());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setRole(user.getRole());
        newUser.setClinic(user.getClinic());

        return newUser;
    }


    /**
     * Convert @User into @Veterinarian
     * @param user @User entity
     * @param veterinarian @Veterinarian entity
     * @return @Veterinarian entity
     */
    private static Veterinarian convertToVeterinarian(User user, Veterinarian veterinarian){
        veterinarian.setSpeciality(user.getSpeciality());
        return veterinarian;
    }

    /**
     * Convert @User into @LabAssistant
     * @param user @User entity
     * @param labAssistant @LabAssistant entity
     * @return @LabAssistant entity
     */
    private static LabAssistant convertToLabAssistant(User user, LabAssistant labAssistant){
        return (LabAssistant)labAssistant;
    }

    /**
     * Convert @User into @Receptionist
     * @param user @User entity
     * @param receptionist @Receptionist entity
     * @return @Receptionist entity
     */
    private static Receptionist convertToReceptionist(User user, Receptionist receptionist){
        return (Receptionist)receptionist;
    }
}
