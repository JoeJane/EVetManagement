package com.vet.manage.util;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.entity.LabAssistant;
import com.vet.manage.model.entity.Receptionist;
import com.vet.manage.model.entity.User;
import com.vet.manage.model.entity.Veterinarian;
import com.vet.manage.util.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * EPatient Converter class
 */
@ExtendWith(MockitoExtension.class)
public class ConverterTest {
    @Test
    @DisplayName("Test ConvertToUser to Veterinarian Success")
    void testConvertToVeterinarianUser() {
        User nurseUser = new User(1, "veterinarian", "password", Role.VETERINARIAN);
        User convertedUser = Converter.convert(nurseUser);

        Assertions.assertTrue(convertedUser instanceof Veterinarian);
    }

    @Test
    @DisplayName("Test ConvertToUser to LabAssistant Success")
    void testConvertToLabAssistantUser() {
        User labAssistantUser = new User(1, "labassistant", "password", Role.LAB_ASSISTANT);
        User convertedUser = Converter.convert(labAssistantUser);

        Assertions.assertTrue(convertedUser instanceof LabAssistant);
    }


    @Test
    @DisplayName("Test ConvertToReceptionistUser to Receptionist Success")
    void testConvertToReceptionistUser() {
        User receptionistUser = new User(1, "receptionist", "password", Role.RECEPTIONIST);
        User convertedUser = Converter.convert(receptionistUser);

        Assertions.assertTrue(convertedUser instanceof Receptionist);
    }
}
