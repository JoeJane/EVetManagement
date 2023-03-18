package com.vet.manage.model.dto;


/**
 * POJO for capture search fields
 * @author Sinthuvarsini
 */
public class SearchTerm {
    String value;
    String status;
    String []ids;
    Role role;

    public SearchTerm(String value, String status){
        this.value = value;
        this.status = status;
    }

    public SearchTerm(String []ids, String status){
        this.ids = ids;
        this.status = status;
    }

    public SearchTerm(){}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
