package com.vet.manage.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity for Receptionist
 * @author Josef
 */
@Entity
@Table(name = "receptionist")
public class Receptionist extends User{

    @OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL)
    private List<Appointment> appointments;


    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


}
