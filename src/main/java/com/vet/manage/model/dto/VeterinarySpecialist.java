package com.vet.manage.model.dto;

/**
 * Predefine VeterinarySpecialist
 */
public enum VeterinarySpecialist {
    ANESTHESIOLOGISTS(" Veterinary Anesthesiologist"),
    DENTISTRY("Veterinary dentist"),
    DERMATOLOGISTS("Veterinary dermatologist"),
    BEHAVIORIST("Veterinary behaviorist"),
    SURGEON("Veterinary surgeon"),
    PRACTITIONER("Veterinary Practitioner"),
    EMERGENCY_MEDICINE_SPECIALISTS("Emergency and critical care"),
    FAMILY_PHYSICIANS("Family Physicians");

    private final String value;

    private VeterinarySpecialist(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
