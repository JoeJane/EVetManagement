package com.vet.manage.model.entity;

import com.vet.manage.model.dto.VeterinarySpecialist;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Veterinarian
 */
@Entity
@Table(name = "veterinarian")
public class Veterinarian extends User {

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "speciality")
    private VeterinarySpecialist speciality;

    @OneToMany(mappedBy = "veterinarian", cascade = CascadeType.ALL)
    private List<Appointment> appointments;


    public VeterinarySpecialist getSpeciality() {
        return speciality;
    }

    public void setSpeciality(VeterinarySpecialist speciality) {
        this.speciality = speciality;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


}
