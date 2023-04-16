package com.vet.manage.model.entity;

import javax.persistence.*;

/**
 * Entity for Report
 */
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pet_id")
    private Pet pet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="appointment_id")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lab_assistant_id")
    private LabAssistant labAssistant;

    @Lob
    @Column(name = "file")
    private byte[] data;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileType")
    private String fileType;

    public Report() {
    }

    public Report(byte[] data, String fileName, String fileType) {
        this.data = data;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public LabAssistant getLabAssistant() {
        return labAssistant;
    }

    public void setLabAssistant(LabAssistant labAssistant) {
        this.labAssistant = labAssistant;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
