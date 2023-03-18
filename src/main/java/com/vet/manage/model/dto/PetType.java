package com.vet.manage.model.dto;

/**
 * Predefine pet types
 * @author Jaswinder
 */
public enum PetType {
    DOG("Dog"),
    CAT("Cat"),
    RABBIT("Rabbit");

    private final String value;

    private PetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
