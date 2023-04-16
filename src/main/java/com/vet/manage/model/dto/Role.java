package com.vet.manage.model.dto;


/**
 * Predefine roles
 * @author Jane Aarthy Joseph
 */
public enum Role {
    USER("User"),
    ADMIN("Admin"),
    VETERINARIAN("Veterinarian"),
    LAB_ASSISTANT("Lab Assistant"),
    RECEPTIONIST("Receptionist");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
