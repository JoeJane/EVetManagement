package com.vet.manage.model.entity;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * Entity for Owner
 * @author Maiara Almeida
 */
@Entity
@Table(name = "owner")
public class Owner extends User {
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @Valid
    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
