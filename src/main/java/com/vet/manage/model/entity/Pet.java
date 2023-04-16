package com.vet.manage.model.entity;

import com.vet.manage.model.dto.PetType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity for Pet
 * @author Jaswinder
 */
@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Pet type is mandatory")
    @Column(name = "type")
    private PetType type;

    @NotBlank(message = "Breed cannot be empty")
    @Column(name = "breed")
    private String breed;

    @NotNull(message = "Invalid age")
    @PositiveOrZero(message = "Age should zero or positive")
    @Column(name = "age")
    private Integer age;

    @NotNull(message = "Invalid weight")
    @PositiveOrZero(message = "Weight should zero or positive")
    @Column(name = "weight")
    private Integer weight;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "userId")
    private Owner owner;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    @OrderBy("appointmentDate DESC, appointmentTime DESC")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Report> reports;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinFormula("(" +
            "SELECT max(a.id) " +
            "FROM Appointment a " +
            "WHERE a.pet_id = id " +
            "LIMIT 1" +
            ")")
    private Appointment latestAppointment;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Appointment getLatestAppointment() {
        return latestAppointment;
    }

    public void setLatestAppointment(Appointment latestAppointment) {
        this.latestAppointment = latestAppointment;
    }


}
