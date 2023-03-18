package com.vet.manage.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity for LabAssistant
 * @author Maiara Almeida
 */
@Entity
@Table(name = "lab_assistant")
public class LabAssistant extends User {
    @OneToMany(mappedBy = "labAssistant", cascade = CascadeType.ALL)
    private List<Report> reports;


    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
